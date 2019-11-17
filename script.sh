#!/bin/bash

#script to recursively travel a dir of n levels

function traverse() {
for file in "$1"/*
do
    if [ ! -d "${file}" ] ; then
	if [[ ${file} == *.kt ]] ; then
		echo "${file} is kotlin file"
		echo "${file} ####  ERROR LOGS   ####  "  >> error.log
		javac "${file}" >> error.log 2>> error.log
#	else
#        	# echo "${file} is a file"
	fi
    else
        echo "entering recursion with: ${file}"
        traverse "${file}"
    fi
done
}

function main() {
    traverse "$1"
}

main "$1"
