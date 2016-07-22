package ie.lucidlogic.demo.dagger2demo.utils;

/**
 * Created by itowey on 20/07/16.
 */
public class CheckUtils {
    public static void checkNullAndFail(Object object ) {
        if(object == null){
            throw new RuntimeException("DI failed");
        }
    }
}
