# Data set manually typed out
h <- c(1.2,.9,.7,1,1.7,1.7,1.1,.9,1.7,1.9,1.3,2.1,1.6,1.8,1.4,1.3,1.9,1.6,.8,2,1.7,1.6,2.3,2)
p <- c(1.6,1.5,1.1,2.1,1.5,1.3,1,2.6)

boxplot(h,p)

x <- c(rep(1, length(h)), rep(0, length(p)))
y <- c(h,p)

linmod <- lm(y~x)
print(summary(linmod))
