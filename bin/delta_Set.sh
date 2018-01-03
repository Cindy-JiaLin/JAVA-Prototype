# Test Empty Sets
#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_Empty_REAL05.VALUE testVALUE/SET/SET_Empty_REAL05.VALUE

# Test one set is empty, while the other is not
#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_EX5.4.1_A.VALUE testVALUE/SET/SET_Empty_REAL05.VALUE

# Test one set is non-empty, while the other is empty
#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_Empty_REAL05.VALUE testVALUE/SET/SET_EX5.4.1_B.VALUE

# Test both sets are non-emtpy sets
# Implement the Example 5.4.1 on page 79 and the Example 5.4.3 on page 82
java -Xss100M -cp lib dcprototype.DIFF -d testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_EX5.4.1_A.VALUE testVALUE/SET/SET_EX5.4.1_B.VALUE


# Two simple tests
#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_EX5.4.1_A_Simple.VALUE testVALUE/SET/SET_EX5.4.1_B_Simple.VALUE

#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_NAT.TYPE testVALUE/SET/SET_A.VALUE testVALUE/SET/SET_B.VALUE

java -Xss100M -cp lib dcprototype.DIFF -d testTYPE/SET/SET_PAIRS.TYPE testVALUE/SET/SET_EX5.6.1_f.VALUE testVALUE/SET/SET_EX5.6.1_g.VALUE

#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_CUBOID.TYPE testVALUE/SET/SET_CUBOID_A.VALUE testVALUE/SET/SET_CUBOID_B.VALUE


#java -cp lib dcprototype.DIFF -d testTYPE/SET/SET_NAT.TYPE testVALUE/SET/SET_NAT_A.VALUE testVALUE/SET/SET_NAT_B.VALUE


