
TYPE: UNION(left.UNION(other.NAT|none.UNIT)|right.REC(l:UNION(nil.UNIT|cons.PRODUCT(car.CHAR*cdr.VAR(l)))))
TypeT 1:
left.other.3
TypeT 2:
right.cons.(car.a,cdr.cons.(car.b,cdr.cons.(car.c,cdr.nil.unit)))
Delta :
dismatch: (left-to-right).Chg.(left.other.3,right.cons.(car.a,cdr.cons.(car.b,cdr.cons.(car.c,cdr.nil.unit))))
