#!/bin/sh

# ------------------------------------------------------------
# Start script for Starting the Service
# ------------------------------------------------------------
USAGE="USAGE: startup.sh"
DEPLOYDIR=${app.deploy.dir}
JMX_SSL=true
JMX_PORT=${reserved.port.jmx}
APP_NAME="0.1"
RUNTIME_USER="${runtime.user}"
PASSWORD_FILE=${password.file}
TRUSTSTORE_FILE=${truststore.file}
KEYSTORE_FILE=${keystore.file}

export SUNJMX="-Dcom.sun.management.jmxremote.port=$JMX_PORT\
       -Dcom.sun.management.jmxremote.ssl=$JMX_SSL \
       -Dcom.sun.management.jmxremote.authenticate=false"

if [ $JMX_SSL == "true" ]
then

   if (test -f $KEYSTORE_FILE -a -f $TRUSTSTORE_FILE -a -f $PASSWORD_FILE)
   then
      . $PASSWORD_FILE
      SUNJMX="${SUNJMX} \
          -Djavax.net.ssl.keyStore=$KEYSTORE_FILE \
          -Djavax.net.ssl.keyStorePassword=$KEYSTORE_PASSWORD \
          -Djavax.net.ssl.trustStore=$TRUSTSTORE_FILE \
          -Djavax.net.ssl.trustStorePassword=$TRUSTSTORE_PASSWORD"
   else
      echo "ERROR: Required files for the Overstock JMX envrionment cannot be found!"
      echo "Ensure you've got the following files available:"
      echo "	$KEYSTORE_FILE"
      echo "	$TRUSTSTORE_FILE"
      echo "	$PASSWORD_FILE"
      exit 1
   fi
fi

export JAVA_OPTS="$JAVA_OPTS $SUNJMX"

if [ ! -x $JRE_HOME/bin/java ]; then
  JRE_HOME=${jre.home}
  if [ ! -x $JRE_HOME/bin/java ]; then
    echo "ERROR: Unable to find or detect JRE_HOME!"
    exit 1
  fi
fi
export JRE_HOME

# ------------------------------------------------------------
# Verify command line
# ------------------------------------------------------------
if [ "$#" -gt 0 ]
then
  # It appears that the script was invoked with too many arguments
  # Will echo usage to command line and exit
  echo -e $USAGE
  exit 1
fi

# ------------------------------------------------------------
# Setup and configuration
# ------------------------------------------------------------
# Validate necessary files/directories are present
if [ ! -f /opt/ostk/config/xml/global.xml ]; then
     echo "Requires /opt/ostk/config/xml/global.xml be present!"
     exit 1;
fi

if [ ! -d /opt/ostk/drop-box ]; then
   echo "Requires /opt/ostk/drop-box exit!"
   exit 1;
fi

grep -q $RUNTIME_USER /etc/passwd
if [ $? == 1 ];
then
  echo "ERROR: $RUNTIME_USER does not exist!"
  exit 1;
fi


if [ -z $JAVA_HEAP_SIZE ]; then
  JAVA_HEAP_MIN=${java.heap.min}
  JAVA_HEAP_MAX=${java.heap.max}
else
  echo "Heap size overriden to $JAVA_HEAP_SIZE"
  JAVA_HEAP_MIN=$JAVA_HEAP_SIZE
  JAVA_HEAP_MAX=$JAVA_HEAP_SIZE
fi

JAVA_VERSION=`$JRE_HOME/bin/java -version  2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q'`

if [[ "$JAVA_VERSION" > "17" ]]; then
  echo detected Java 1.8 or above, setting MaxMetaspaceSize=${java.metaspace.max}
  JRE_OPTS="$JRE_OPTS -XX:MaxMetaspaceSize=${java.metaspace.max}"
fi

JRE_OPTS="-D$APP_NAME -Xms$JAVA_HEAP_MIN -Xmx$JAVA_HEAP_MAX ${gc.log} -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${app.log.dir}/oom_heap.bin ${jre.opts} $JRE_OPTS"

# Setup runtime command
RUNCMD="$JRE_HOME/bin/java $JAVA_OPTS $JRE_OPTS $SUNJMX -jar ${DEPLOYDIR}/lib/0.1-webapp-${project.version}.jar >> ${DEPLOYDIR}/logs/0.1.console.log 2>&1 &"

# ------------------------------------------------------------
# Launch the App as correct user
# ------------------------------------------------------------
logger -t $APP_NAME "Startup Initiated"

if [ $USER == $RUNTIME_USER ];
then
   exec $RUNCMD
else
  if [ $UID -eq 0 ];
  then
    exec su -m $RUNTIME_USER -c "$RUNCMD"
  else
    echo "ERROR: Not running as $RUNTIME_USER or root!"
    exit 1;
  fi
fi
