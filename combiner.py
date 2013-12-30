first=dict()
second=dict()
f1=open("GSM758735_DN1_miRNA.consolidated.txt")
f2=open("GSM758736_DN2_miRNA.consolidated.txt")
for line in f1:
	line=line.strip()
	fields=line.split(" ")
	first[fields[0]]=fields[1]
for line in f2:
        line=line.strip()
        fields=line.split(" ")
        second[fields[0]]=fields[1]
miRNAs=set()
miRNAs|=(set(first.keys()))
miRNAs|=(set(second.keys()))

for e in miRNAs:
	print(str(e)+"\t"+str(first.get(e,0))+"\t"+str(second.get(e,0)))
