kiama.data <- c(83,17,28,60,51,55,56,61,87,10,8,61,60 ,28 ,95,35 ,47 ,77,25 ,68 ,146,18 ,169 ,25,8,36,89,8,27,17,18,26,15,21,73,11,10,36,69,83,18,18,9,11,16,40,37,42,29,10,10,17,54,7,82,14,91,34,29,9,8,27,8,12)
n <- length(kiama.data)
  
theta.hat <- 1/mean(kiama.data)
information.theta.hat <- n / (theta.hat^2)
theta.hat.se <- 1/sqrt(information.theta.hat) 
theta.hat.ci95 <- list(LB=theta.hat - (1.96*theta.hat.se), UB=theta.hat + (1.96*theta.hat.se))

x <- seq(0,0.1,0.001)
likelihood <- function(theta){
  return(theta^n*exp(-theta*sum(kiama.data)))
} 
plot(x,likelihood(x), xlab = 'theta', ylab = 'likelihood(theta)', pch=19, type = 'l')
x[which.max(likelihood(x))]


log.likelihood <- function(theta){
  return(n*log(theta) -theta*sum(kiama.data))
} 
plot(x,log.likelihood(x), xlab = 'theta', ylab = 'log-likelihood(theta)', pch=19, type = 'l')

text(x[which.max(log.likelihood(x))], log.likelihood(x[which.max(log.likelihood(x))]), 
     paste('(',x[which.max(log.likelihood(x))],',', round(log.likelihood(x[which.max(log.likelihood(x))]),2),')'), cex=1, pos=1, col="black" )

#tangent to the maximum of the function
abline(a=log.likelihood(x[which.max(log.likelihood(x))]), b=0, lwd=0.5, lty=6)

hist(kiama.data, probability = TRUE)
xseq <- seq(0,max(kiama.data),length=101)
points(xseq,dexp(xseq,theta.hat),col="purple",type="l")
points(xseq,dexp(xseq,theta.hat.ci95$UB),col="red",type="l",lty=3)
points(xseq,dexp(xseq,theta.hat.ci95$LB),col="green",type="l",lty=3)

lines(x,n/(x^2))


loglik <- function(theta,x){
  sum(dexp(x,theta,log=TRUE))
}
theta0 <- 0.03
fit <- optim(par=theta0,fn=loglik,method="BFGS",x=kiama.data,control=list(fnscale=-1),hessian=TRUE)

fit$se <- 1/sqrt(-fit$hessian)

#providing the gradient (first direviative speeds up computation)
score <- function(theta,x)
{
  n<-length(x)
  n/theta-sum(x)
}
fit <- optim(par=theta0,fn=loglik,gr=score,method="BFGS",x=kiama.data,control=list(fnscale=-1)
             ,hessian=TRUE)




#
# wk2 lecture 1

#
#



loglik <- function(theta,x)
{
  alpha <- theta[1]
  beta <- theta[2]
  sum(dweibull(x,alpha,beta,log=TRUE))
}

loglik(theta0, kiama.data)

alpha0 <- 1
beta0 <- mean(kiama.data)
theta0 <- c(alpha0,beta0)
fit <- optim(par=theta0,loglik,method="BFGS",x=kiama.data,control=list(fnscale=-1),hessian=TRUE)

alphagrid <- seq(0.8,1.6,length=41)
betagrid <- seq(30,60,length=41)
thetagrid <- expand.grid(alphagrid,betagrid)
thetagrid <- as.matrix(thetagrid)
lvec<-rep(NA,41^2)
for (i in 1:nrow(thetagrid))
{
  lvec[i] <- loglik(thetagrid[i,],kiama.data)
}
lmat <- matrix(lvec,41,41)
image(alphagrid,betagrid,lmat,col=terrain.colors(12),xlab="alpha",ylab="beta")
contour(alphagrid,betagrid,lmat,add=TRUE)
points(fit$par[1],fit$par[2],pch=3,col="red")
text(fit$par[1],fit$par[2], 
     'MLE using \nweibull model', cex=1, pos=1, col="black" )
points(theta0[1],theta0[2],pch=3,col="blue")
text(theta0[1],theta0[2], 
     'MLE using \nexponential model', cex=1, pos=1, col="black" )



inf<- (-fit$hessian)
fit$se <- sqrt(diag(solve(inf)))
fit$alpha.ci95 <- list(LB=fit$par[1] - fit$se[1], UB=fit$par[1] + fit$se[1])
fit$beta.ci95 <- list(LB=fit$par[2] - fit$se[2], UB=fit$par[2] + fit$se[2])


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
