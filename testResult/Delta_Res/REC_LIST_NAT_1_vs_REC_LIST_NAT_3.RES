
TYPE: REC(l:UNION(nil.UNIT|lst.PRODUCT(car.NAT*cdr.VAR(l))))
TypeT 1:
lst.(car.1,cdr.lst.(car.2,cdr.lst.(car.3,cdr.lst.(car.4,cdr.nil.unit))))
TypeT 2:
lst.(car.5,cdr.lst.(car.1,cdr.lst.(car.2,cdr.lst.(car.3,cdr.lst.(car.4,cdr.nil.unit)))))
Delta :
lst.DeltaProduct:(Chg.(1,5), lst.DeltaProduct:(Chg.(2,1), lst.DeltaProduct:(Chg.(3,2), lst.DeltaProduct:(Chg.(4,3), nil-to-lst.Chg.(nil.unit,lst.(car.4,cdr.nil.unit))))))
