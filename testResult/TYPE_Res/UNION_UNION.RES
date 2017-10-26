
FileName: testTYPE/UNION/UNION_UNION.TYPE
parseTYPE: 
UNION(left.UNION(other.NAT|none.UNIT)|right.REC(l:UNION(nil.UNIT|cons.PRODUCT(car.CHAR*cdr.VAR(l)))))
Unfold this type: 
UNION(left.UNION(other.NAT|none.UNIT)|right.UNION(nil.UNIT|cons.PRODUCT(car.CHAR*cdr.REC(l:UNION(nil.UNIT|cons.PRODUCT(car.CHAR*cdr.VAR(l)))))))
