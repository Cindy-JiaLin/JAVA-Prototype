# Test Empty Multisets
java -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/MSET/MSET_Empty_REAL05.VALUE testVALUE/MSET/MSET_Empty_REAL05.VALUE
# Test one multiset is empty, while the other is not
java -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/MSET/MSET_EX5.5.1_A.VALUE testVALUE/MSET/MSET_Empty_REAL05.VALUE
# Test one multiset is non-empty, while the other is empty
java -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/MSET/MSET_Empty_REAL05.VALUE testVALUE/MSET/MSET_EX5.5.1_B.VALUE
# Test both multisets are non-emtpy sets
# Implement the Example 5.5.1 on page 86 and the Example 5.4.3 on page 89
#java -Xss5M -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/MSET/MSET_EX5.5.1_A.VALUE testVALUE/MSET/MSET_EX5.5.1_B.VALUE

java -Xss5M -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/MSET/MSET_EX5.5.1_A_Simple.VALUE testVALUE/MSET/MSET_EX5.5.1_B_Simple.VALUE

