#!/bin/bash
#smirk.bash
#The program to automatically apply the Java-based normalization program and the R-based heatmap and analysis
#Brandon Milholland

input=${1/.csv/}

#First, generate the info table for the normalization program

echo "Sample,Total Reads"> ${input}.info.csv
head -n1 ${input}.csv | tr "," "\n" | awk '(NF>0)' > tmp1_${$}
< ${input}.csv awk -v FS="," '{for(i=2;i<=NF;i++){sums[i]+=$i}}END{for(i in sums){print sums[i]}}' > tmp2_${$}
paste -d"," tmp1_${$} tmp2_${$} >> ${input}.info.csv

#Next, perform the normalization

java wasp_normalize ${input}.csv ${input}.info.csv


if [[ -z "$2" ]]
then
    suffix="directNormal.csv"
    else
        if [ $2 == "r" ]
	then
	    suffix="readOut.csv"
	else
	    suffix="directNormal.csv"
	fi
fi



< ${input}.csv${suffix} python3 remove_low.py | sed s/"miRNA,"// > ${input}.heatmap_table.csv #Remove the first column of the first line, to make the R import it as row.names

Rscript smirk.R ${input}.heatmap_table.csv




rm tmp1_${$}
rm tmp2_${$}


