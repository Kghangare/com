

data1 <- read.csv(file.choose())

View(data1)

subset_mydata <- data1[,c(1,5)]

View(subset_mydata)

x = which.max(subset_mydata$snowfall)
View(x)

subset_mydata[x,]

# or

subset(subset_mydata, snowfall == max(snowfall))
