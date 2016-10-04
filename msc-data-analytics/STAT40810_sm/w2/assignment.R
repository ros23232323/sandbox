data <- data.frame(list(num=c(0,1,2,3,4,5,7,9)),list(freq=c(584,398,168,35,9,4,1,1)))

dPoissonGamma <- function(x,alpha,beta,log=FALSE)
{
  if (log)
  {
    lognumer <- lgamma(x+alpha)+x*log(beta)
    logdenom <- lgamma(alpha)+(alpha+x)*log(1+beta)+lgamma(x+1)
    res <- lognumer-logdenom
  }else
  {
    numer <- gamma(x+alpha)*beta^x
    denom <- gamma(alpha)*(1+beta)^(alpha+x)*gamma(x+1)
    res <- numer/denom
  }
  res
}

dPoissonGamma_logtrue <- function(theta, x){
  alpha <- theta[1]
  beta <- theta[2]
  sum(dPoissonGamma(x,alpha,beta,TRUE))
}

x <- data$freq
alpha0<-.5
beta0<-.1
theta0<-c(alpha0,beta0)
fit<-optim(par=theta0,fn=dPoissonGamma_logtrue,method="BFGS",x=x,control=list(fnscale=-1,maxit=10000),hessian=TRUE)
fit

dPoissonGamma_logtrue(c(1,.00001),data$freq)

dPoi_logtrue <- function(lambda, x) {
  sum(dpois(x, lambda, log = TRUE))
}

x <- data$freq
lambda0 <- 1
fit<-optim(par=lambda0,fn=dPoi_logtrue,method="BFGS",x=x,control=list(fnscale=-1),hessian=FALSE)
fit

