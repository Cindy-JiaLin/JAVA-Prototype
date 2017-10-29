# Test two emtpy lists
java -cp lib dcprototype.DIFF -d testTYPE/LIST/LIST_NAT.TYPE testVALUE/LIST/LIST_NAT_Empty.VALUE testVALUE/LIST/LIST_NAT_Empty.VALUE
# Test one empty list with the other non-empty list
java -cp lib dcprototype.DIFF -d testTYPE/LIST/LIST_NAT.TYPE testVALUE/LIST/LIST_NAT_Empty.VALUE testVALUE/LIST/LIST_NAT_1.VALUE
# Test one non-empty list with the other empty list
java -cp lib dcprototype.DIFF -d testTYPE/LIST/LIST_NAT.TYPE testVALUE/LIST/LIST_NAT_1.VALUE testVALUE/LIST/LIST_NAT_Empty.VALUE
# Test two non-empty lists
java -cp lib dcprototype.DIFF -d testTYPE/LIST/LIST_NAT.TYPE testVALUE/LIST/LIST_NAT_1.VALUE testVALUE/LIST/LIST_NAT_2.VALUE


