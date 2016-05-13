###################################################################################################
#Codes used in Chapter 1 of book Learning Bayesian Models with R                                  #
#Author Ian Towey                                                          #
###################################################################################################


x <- rgamma(100000,shape=4, scale=1)
length(x[x < 4])/length(x)
length(x[x > 4])/length(x)
hist(x)
summary(x)
install.packages("Hmisc")
library(Hmisc)
describe(x) 
install.packages("pastecs")
library(pastecs)
stat.desc(x)
install.packages("psych")
library(psych)
describe(x)

x <- rnorm(100000,mean=4, sd=1)
length(x[x < 4])/length(x)
length(x[x > 4])/length(x)
hist(x)


x <- rbeta(100000,shape1=4, shape2=10)
length(x[x < 4])/length(x)
length(x[x > 4])/length(x)
hist(x)
