# Test empty rec
java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_LIST_NAT.TYPE testVALUE/REC/REC_empty.VALUE testVALUE/REC/REC_empty.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_TREE_NAT.TYPE testVALUE/REC/REC_emptyTree.VALUE testVALUE/REC/REC_emptyTree.VALUE

# Test and get delta of values of Recursive LIST OF NATs
java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_LIST_NAT.TYPE testVALUE/REC/REC_LIST_NAT_1.VALUE testVALUE/REC/REC_LIST_NAT_1.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_LIST_NAT.TYPE testVALUE/REC/REC_LIST_NAT_1.VALUE testVALUE/REC/REC_LIST_NAT_2.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_LIST_NAT.TYPE testVALUE/REC/REC_LIST_NAT_1.VALUE testVALUE/REC/REC_LIST_NAT_3.VALUE

# Test and get delta of values of Recursive TREE OF NATs
java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_TREE_NAT.TYPE testVALUE/REC/REC_TREE_NAT_1.VALUE testVALUE/REC/REC_TREE_NAT_1.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/REC/REC_TREE_NAT.TYPE testVALUE/REC/REC_TREE_NAT_1.VALUE testVALUE/REC/REC_TREE_NAT_2.VALUE

