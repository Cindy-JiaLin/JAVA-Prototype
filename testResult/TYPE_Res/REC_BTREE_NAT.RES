
FileName: testTYPE/REC/REC_BTREE_NAT.TYPE
parseTYPE: 
REC(b:UNION(emptyBTREE.UNIT|btree.PRODUCT(fst.NAT*snd.VAR(b)*trd.VAR(b))))
Unfold this type: 
UNION(emptyBTREE.UNIT|btree.PRODUCT(fst.NAT*snd.REC(b:UNION(emptyBTREE.UNIT|btree.PRODUCT(fst.NAT*snd.VAR(b)*trd.VAR(b))))*trd.REC(b:UNION(emptyBTREE.UNIT|btree.PRODUCT(fst.NAT*snd.VAR(b)*trd.VAR(b))))))
