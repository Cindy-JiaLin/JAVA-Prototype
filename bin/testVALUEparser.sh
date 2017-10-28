# Test testVALUEparser
# Parse values of PRIMITIVE TYPEs
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/UNIT.TYPE testVALUE/PRIMITIVE/UNIT.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/BOOL.TYPE testVALUE/PRIMITIVE/BOOL_true.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/BOOL.TYPE testVALUE/PRIMITIVE/BOOL_false.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/NAT.TYPE testVALUE/PRIMITIVE/NAT_23.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/NAT.TYPE testVALUE/PRIMITIVE/NAT_123.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/CHAR.TYPE testVALUE/PRIMITIVE/CHAR_s.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/CHAR.TYPE testVALUE/PRIMITIVE/CHAR_t.VALUE


java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/REAL02.TYPE testVALUE/PRIMITIVE/REAL_208.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/REAL05.TYPE testVALUE/PRIMITIVE/REAL_208.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRIMITIVE/REAL5.TYPE testVALUE/PRIMITIVE/REAL_209.VALUE

# Parse values of PRODUCT TYPEs
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/PAIR.TYPE testVALUE/PRODUCT/PAIR_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/PAIR.TYPE testVALUE/PRODUCT/PAIR_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/PAIR_UNIT.TYPE testVALUE/PRODUCT/PAIR_UNIT.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/TRIPLE.TYPE testVALUE/PRODUCT/TRIPLE_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/TRIPLE.TYPE testVALUE/PRODUCT/TRIPLE_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/PRODUCT.TYPE testVALUE/PRODUCT/PRODUCT_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/PRODUCT/PRODUCT.TYPE testVALUE/PRODUCT/PRODUCT_2.VALUE

# Parse values of UNION TYPEs
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION.TYPE testVALUE/UNION/UNION_left_NAT_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION.TYPE testVALUE/UNION/UNION_left_NAT_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION.TYPE testVALUE/UNION/UNION_right_PAIR_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION.TYPE testVALUE/UNION/UNION_right_PAIR_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION_LEFT_LIST_CHAR.TYPE testVALUE/UNION/UNION_left_LIST_CHAR_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION_LEFT_LIST_CHAR.TYPE testVALUE/UNION/UNION_left_LIST_CHAR_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION_UNION.TYPE testVALUE/UNION/UNION_UNION_left_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION_UNION.TYPE testVALUE/UNION/UNION_UNION_left_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/UNION/UNION_UNION.TYPE testVALUE/UNION/UNION_UNION_right.VALUE

# Parse values of REC TYPEs (Recursive Types)
java -cp lib dcprototype.DIFF -v testTYPE/REC/REC_LIST_NAT.TYPE testVALUE/REC/REC_LIST_NAT_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/REC/REC_LIST_NAT.TYPE testVALUE/REC/REC_LIST_NAT_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/REC/REC_LIST_PAIR_NAT_CHAR.TYPE testVALUE/REC/REC_LIST_PAIR_NAT_CHAR.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/REC/REC_TREE_NAT.TYPE testVALUE/REC/REC_TREE_NAT_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/REC/REC_TREE_NAT.TYPE testVALUE/REC/REC_TREE_NAT_2.VALUE

# Parse values of SETs
java -cp lib dcprototype.DIFF -v testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_EX5.4.1_A.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/SET/SET_REAL05.TYPE testVALUE/SET/SET_EX5.4.1_B.VALUE

# Parse values of LIST TYPEs (Not REC TYPE)
java -cp lib dcprototype.DIFF -v testTYPE/LIST/LIST_NAT.TYPE testVALUE/LIST/LIST_NAT_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/LIST/LIST_NAT.TYPE testVALUE/LIST/LIST_NAT_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/LIST/LIST_CHAR.TYPE testVALUE/LIST/LIST_CHAR_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/LIST/LIST_CHAR.TYPE testVALUE/LIST/LIST_CHAR_2.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/LIST/LIST_PAIR_NAT_CHAR.TYPE testVALUE/LIST/LIST_PAIR_NAT_CHAR_1.VALUE
java -cp lib dcprototype.DIFF -v testTYPE/LIST/LIST_PAIR_NAT_CHAR.TYPE testVALUE/LIST/LIST_PAIR_NAT_CHAR_2.VALUE

# Parse values of MAPPING TYPEs
java -cp lib dcprototype.DIFF -v testTYPE/MAPPING/MAPPING_NAT_CHAR.TYPE testVALUE/MAPPING/MAPPING_dom_NAT_cod_CHAR.VALUE

