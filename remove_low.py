import sys


threshold=10 #Threshold below which an miRNA is considered too low

def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        return False

for line in sys.stdin:
    fields=line.split(",")
    if(fields[0]=="miRNA"):
        print(line, end="") #Print header unprocessed
        continue
    low_count=0
    total_count=0
    for field in fields:
        if not is_number(field):
            continue
        if(int(field)<10):
            low_count+=1
        total_count+=1
    if(low_count/total_count<.5): 
        print(line, end="") #Only print if low counts make up less than half of the samples
