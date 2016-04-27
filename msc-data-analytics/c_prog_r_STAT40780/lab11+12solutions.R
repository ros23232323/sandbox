
library(Rcpp)
library(inline)
library(RcppArmadillo)
library(rbenchmark)





#QUESTION 2
#count number of missing values in a vector


body_countMiss <- '
 NumericVector xx(x);
 LogicalVector y = is_na(xx);
 int result = std::count(y.begin(),y.end(), true);
 return wrap(result);
'

countMiss <- cxxfunction( signature(x = "numeric"),
                          body = body_countMiss,
                          plugin= "Rcpp")

x <- rnorm(100)
#set 10 values from x to zero
x[sample(1:100, size = 10, replace = FALSE)] <- NA
countMiss(x)






#QUESTION 3
#merge two sorted vectors into a longer sorted vector


body_mergeTwo <- '
 NumericVector xx = clone(x);
 NumericVector yy = clone(y);
 std::sort(xx.begin(), xx.end());
 std::sort(yy.begin(), yy.end());
 NumericVector out = NumericVector( xx.size() + yy.size());
 std::merge(xx.begin(), xx.end(), yy.begin(), yy.end(), out.begin());
 return wrap(out);
'

mergeTwo <- cxxfunction( signature(
                          x = "numeric", y = "numeric"),
                          body = body_mergeTwo,
                          plugin= "Rcpp")


x <- rnorm(10)
y <- rnorm(10)
x
y
mergeTwo(x,y)





#QUESTION 4
#centering a matrix
centerRcpp <- cxxfunction( signature(X="numeric"),
                   body = '
                     mat M = as<mat>(X);
                     //compute mean of each col
                     rowvec mu = mean(M, 0);
                     //subtract mean vector from each row
                     M.each_row() -= mu;
                     return wrap(M);
                    ', 
                   include = 'using namespace arma;',
                   plugin = "RcppArmadillo")




data(iris)  #load iris data into R
names(iris) #extract names of variables in the data.frame
?iris  #get information on the iris dataset

#remove last column of the iris data.frame, 
#which contains the categorical variable Species
Y <-  as.matrix( iris[,-5] )
#call function and save centered data as centY
centY <- centerRcpp( Y )
#view first few rows 
head(centY)
#mean of each column should be 0 if centering succeeded
round( apply(centY,2,mean) , 10)





#QUESTION 5
#Outer product of two vectors
op <- cxxfunction( signature(x="numeric", y = "numeric"),
                    body = '
                     vec v1 = as<vec>(x);
                     vec v2 = as<vec>(y);
                     mat M = v1 * v2.t();
                     return wrap(M);
                    ', 
                    include = 'using namespace arma;',
                    plugin = "RcppArmadillo")

x <- rnorm(5)
y <- rnorm(3)
op(x,y)
