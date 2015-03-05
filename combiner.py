import sys

filenames=("GSM758731_LSK_miRNA.consolidated.txt",
"GSM758732_MPP_miRNA.consolidated.txt",
"GSM758733_LMPP_miRNA.consolidated.txt",
"GSM758734_ProB_miRNA.consolidated.txt",
"GSM758735_DN1_miRNA.consolidated.txt",
"GSM758736_DN2_miRNA.consolidated.txt",
"GSM758737_DN3_miRNA.consolidated.txt",
"GSM758738_DN4_miRNA.consolidated.txt",
"GSM758739_DP_miRNA.consolidated.txt",
"GSM758740_CD4SP_miRNA.consolidated.txt",
"GSM758741_CD8SP_miRNA.consolidated.txt",
"GSM758742_MEF_miRNA.consolidated.txt")

files=dict()

#Open a file for each filename
for name in filenames:
    files[name]=open(name)
    
dicts=dict()

#For each file, make a dictionary of miRNA->reads
for fn in filenames:
    f=files[fn]
    fdict=dict()
    for line in f:
        line=line.strip()
        fields=line.split(" ")
        fdict[fields[0]]=fields[1]
    dicts[fn]=fdict


miRNAs=set()
for d in dicts.values(): #Make a set of all miRNAs
    miRNAs|=d.keys()



#Print header with names of samples
header=list()
header.append("miRNA")
for n in filenames:
    header.append(n)
print(",".join(header))
    
for e in miRNAs: #Print out every value for every file
    line=list()
    line.append(str(e))
    for n in filenames:
        d=dicts[n]
        line.append(str(d.get(e,0)))
    print(",".join(line))
	


    #	print(str(e)+"\t"+str(first.get(e,0))+"\t"+str(second.get(e,0)))

    
