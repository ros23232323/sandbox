###############################################################################################
###############################################################################################
##  Information to information retrival : Section 21.2 PageRank example
###############################################################################################
###############################################################################################

N <- 7
damping_factor <- 0.15
epsilon <-  1e-10

#principal left eigenvector initial; value
principal_left_eigenvector <- matrix(c(0.5,0,0.2,0,0,0.2,0.1), ncol = N, byrow = FALSE)

#Adjancy Matrix data
data <- c(0,0,1,0,0,0,0,0,1,1,0,0,0,0,1,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,0,1,1,0,1)
Adjancy_Matrix <- matrix(data, nrow = N, ncol = N, byrow = TRUE)

#Markov chain - initial transition probability matrix
tmp <- Adjancy_Matrix/rowSums(Adjancy_Matrix)
Transition_Probability_Matrix <- (1 - damping_factor) * tmp + damping_factor/N

func
currSOSValue <- sum(principal_left_eigenvector[1,]^2) 
i <- 1

repeat {
  prevSOSValue <- currSOSValue
  x_i <- matrix(principal_left_eigenvector[i,], ncol=N, byrow=FALSE)
  principal_left_eigenvector <- rbind(principal_left_eigenvector, x_i%*%Transition_Probability_Matrix)
  i <- i + 1
  print(paste("iteration ",i))
  currSOSValue <-  sum(principal_left_eigenvector[i,]^2)
  if(abs(prevSOSValue - currSOSValue) < epsilon){
    print("Finished")
    break
  }
}

#Principal left eigenvector,corresponding to its largest eigenvalue (which is 1) : PageRank vector of this matrix is:
round(principal_left_eigenvector[nrow(principal_left_eigenvector),],2)

#power iteration method, sttionary markov distribution attained after T iteration's
library("expm")
repeat {
  prevSOSValue <- currSOSValue
  x_0 <- matrix(principal_left_eigenvector[1,], ncol=N, byrow=FALSE)
  principal_left_eigenvector <- rbind(principal_left_eigenvector, x_0%*%(Transition_Probability_Matrix%^%i))
  i <- i + 1
  print(paste("iteration ",i))
  currSOSValue <-  sum(principal_left_eigenvector[i,]^2)
  if(abs(prevSOSValue - currSOSValue) < epsilon){
    print("Finished")
    break
  }
}



#Principal left eigenvector,corresponding to its largest eigenvalue (which is 1) : PageRank vector of this matrix is:
round(principal_left_eigenvector[nrow(principal_left_eigenvector),],2)


