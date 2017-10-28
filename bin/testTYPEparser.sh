# Parse TYPEs
# Parse PRMITIVE TYPEs
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/UNIT.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/BOOL.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/NAT.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/CHAR.TYPE


java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/REAL02.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/REAL05.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/REAL5.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRIMITIVE/REAL100.TYPE
 
# Parse PRODUCT TYPEs
java -cp lib dcprototype.DIFF -t testTYPE/PRODUCT/PAIR.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRODUCT/PAIR_UNIT.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRODUCT/TRIPLE.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/PRODUCT/PRODUCT.TYPE

# Parse UNION TYPEs
java -cp lib dcprototype.DIFF -t testTYPE/UNION/UNION.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/UNION/UNION_LEFT_LIST_CHAR.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/UNION/UNION_UNION.TYPE

# Parse LIST TYPEs
java -cp lib dcprototype.DIFF -t testTYPE/LIST/LIST_NAT.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/LIST/LIST_CHAR.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/LIST/LIST_PAIR_NAT_CHAR.TYPE

# Parse REC TYPEs (Recursive Types) 
java -cp lib dcprototype.DIFF -t testTYPE/REC/REC_NAT.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/REC/REC_LIST_NAT.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/REC/REC_LIST_PAIR_NAT_CHAR.TYPE
java -cp lib dcprototype.DIFF -t testTYPE/REC/REC_BTREE_NAT.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/REC/REC_TREE_NAT.TYPE 
 
# Parse TYPE of SETs
java -cp lib dcprototype.DIFF -t testTYPE/SET/SET_NAT.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/SET/SET_REAL05.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/SET/SET_CHAR.TYPE 
java -cp lib dcprototype.DIFF -t testTYPE/SET/SET_PAIR_NAT_CHAR.TYPE

# Parse TYPE of MAPPINGs
java -cp lib dcprototype.DIFF -t testTYPE/MAPPING/MAPPING_NAT_CHAR.TYPE
