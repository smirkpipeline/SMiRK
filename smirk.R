#smirk.R
#Do R-based data analysis for the SMiRK pipeline
#Brandon Milholland






data.file=commandArgs()[6]

 



hmcols=colorRampPalette(c("green","yellow","red"))(100) #Establish the preferred color theme for the heatmap

heatmap.data <- read.csv(data.file)
x=as.matrix(heatmap.data)

output=paste(data.file,"_clustered_heatmap.png",sep="")

png(output,width = 1280, height = 929)

heatmap(x,col=hmcols,scale="row") # Make clustered hetmap

dev.off()

output=paste(data.file,"_unclustered_heatmap.png",sep="")

png(output,width = 1280, height = 929)

heatmap(x,col=hmcols,scale="row",Rowv=NA,Colv=NA) # Make unclustered hetmap

dev.off()
