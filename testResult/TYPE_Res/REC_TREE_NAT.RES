
FileName: testTYPE/REC/REC_TREE_NAT.TYPE
parseTYPE: 
REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
Unfold this type: 
UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))*cdr.VAR(n))))))
