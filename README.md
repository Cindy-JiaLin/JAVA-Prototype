# JAVA-Prototype
A DIFF program is developed to build values in terms of their types respectively and identify and present the difference between two values of the same data type.

Program name: DIFF

Usage: DIFF [options] [args...] (to execute DIFF) Where options include:

-t typefile

Parse and present a type, in which the typefile is the path and name of a file
containing the text representation of the corresponding type.

-v typefile valuefile

Build and present a value in terms of its type, in which the valuefile is the path and name of a file containing the text representation of this value and the typefile is the path and name of a file containing the text representation of its type. The type and value must be matched, otherwise, a error message will be returned.

-d typefile valuefile1 valuefile2

Identify the difference between two values of the same type. The typefile is the path and name of a file containing the text representation of a type. And the valuefile1 and the valuefile2 are the path and name of two files respectively, these two files contain the text representation of values of the corresponding given type in typefile. These two values must be of the same type, and this type and each must be matched, otherwise, a error message will be returned.

Shell script (.sh) files used to test TYPE and value parsers, get and present the difference between two values are in bin folder.

./bin/compile.sh to compile source code

./bin/testTYPEparser.sh to parse all given TYPEs in testTYPE

./bin/testVALUEparser.sh to parse all given VALUEs with their TYPE in testVALUE

./bin/testExamples.sh to test Examples in the Thesis

Examples for the differnece and the similarity:
1. Sets: Example 5.4.1 on page 79 and Example 5.4.3 on page 82
2. Multisets: Example 5.5.1 on page 86 and Example 5.5.3 on page 89
3. Mappings: Example 5.6.1 on page 92 and Example 5.6.2 on page 94
