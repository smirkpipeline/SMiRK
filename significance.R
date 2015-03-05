#smirk.R
#Do R-based data analysis for the SMiRK pipeline
#Brandon Milholland






data.file=commandArgs()[6]
group1.file=commandArgs()[7]
group2.file=commandArgs()[8]

mirna.data=read.csv(data.file)


g1samples=read.table(group1.file)
g2samples=read.table(group2.file)



g1=subset(mirna.data,select=as.vector(g1samples$V1))
g2=subset(mirna.data,select=as.vector(g2samples$V1))


for(i in seq(1,length(mirna.data[,1])))
{
cat(rownames(g1[i,]),wilcox.test(as.numeric(g1[i,]),as.numeric(g2[i,]),exact=F)$p.val,"\n",sep="\t")

}
