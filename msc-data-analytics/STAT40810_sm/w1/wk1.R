#################################Crime Statistics

x <- c(38 ,34 ,32 ,34 ,32 ,27 ,28 ,36 ,37 ,33)

#method of moments
xbar <- mean(x)  
s2 <- var(x)

phat <- 1 - s2/xbar
nhat <- xbar/phat

phat*nhat

#maximum likelihood
phat1 <- function(x_obs,n){
  m <- length(x_obs)
  sum(x_obs)/(n*m)
}


log_likeihood_bin <- function(x_obs,n,p){
  sum(dbinom(x_obs, n, p, log = TRUE)) #maximum likelihood estimator 
}

y <- c()
n_vals <- seq(1,100,1)
for(n_i in n_vals){
  y[n_i] <- log_likeihood_bin(x,n_i,phat1(x,n_i))
}

y

plot(n_vals,y,pch=3,xlab="n",ylab="Log-likelihood (l)")
idx_of_max <- which(y==max(y[!is.nan(y)]))
abline(v=idx_of_max,col="gray",lty=1)
text(60,-29,"function maximized at (n,p)=(49,0.68)",col="blue")

#this is a good way to find the model parameters, estimate the parameters for a simulated number of 
#parameter values and chhose the value of the model parameters that maximise the function value
#once we have a dataset and thinkabout the model that may suit it we can estimate the model parameters using
#MOM and EM

##################################Taxi Cab Problem
# https://en.wikipedia.org/wiki/Discrete_uniform_distribution
# https://en.wikipedia.org/wiki/German_tank_problem
rm(list=ls())
x <- scan()
127 469 404 148 315 170 271 131

mean(x)
var(x)
min(x) max(x)

runif(10,min = 0,max = )

#assumptions 
#a) equal probability of any taxi being at the airport at any given time
#b) uniform distribution, 1..N, with probability of a car being at the airport being 1/N