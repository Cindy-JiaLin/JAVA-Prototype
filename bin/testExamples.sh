# Test both sets are non-emtpy sets
# Implement the Example 5.4.1 on page 79 and the Example 5.4.3 on page 82
java -Xss100M -cp lib dcprototype.DIFF -d testTYPE/SET/SET_REAL05.TYPE testVALUE/Examples/SET_EX5.4.1_A.VALUE testVALUE/Examples/SET_EX5.4.1_B.VALUE


# Test both multisets are non-emtpy sets
# Implement the Example 5.5.1 on page 86 and the Example 5.4.3 on page 89
#java -Xss200M -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/Examples/MSET_EX5.5.1_A.VALUE testVALUE/Examples/MSET_EX5.5.1_B.VALUE

java -Xss5M -cp lib dcprototype.DIFF -d testTYPE/MSET/MSET_REAL05.TYPE testVALUE/Examples/MSET_EX5.5.1_A_Simple.VALUE testVALUE/Examples/MSET_EX5.5.1_B_Simple.VALUE


# Test both mappings are non-emtpy.
# Implement the Example 5.6.1 on page 79 and the Example 5.6.2 on page 82
#java -Xss100M -cp lib dcprototype.DIFF -d testTYPE/MAPPING/MAPPING_REAL05_REAL05.TYPE testVALUE/Examples/MAPPING_EX5.6.1_A.VALUE testVALUE/Examples/MAPPING_EX5.6.1_B.VALUE

java -Xss5M -cp lib dcprototype.DIFF -d testTYPE/MAPPING/MAPPING_REAL05_REAL05.TYPE testVALUE/Examples/MAPPING_EX5.6.1_A_Simple.VALUE testVALUE/Examples/MAPPING_EX5.6.1_B_Simple.VALUE



