# Test and get delta of values of UNION TYPEs
java -cp lib dcprototype.DIFF -d testTYPE/UNION/UNION.TYPE testVALUE/UNION/UNION_left_NAT_1.VALUE testVALUE/UNION/UNION_left_NAT_2.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/UNION/UNION.TYPE testVALUE/UNION/UNION_right_PAIR_1.VALUE testVALUE/UNION/UNION_right_PAIR_2.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/UNION/UNION_UNION.TYPE testVALUE/UNION/UNION_UNION_left_1.VALUE testVALUE/UNION/UNION_UNION_left_2.VALUE

java -cp lib dcprototype.DIFF -d testTYPE/UNION/UNION_UNION.TYPE testVALUE/UNION/UNION_UNION_left_1.VALUE testVALUE/UNION/UNION_UNION_right.VALUE

