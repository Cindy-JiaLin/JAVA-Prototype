
TYPE: REC(l:UNION(nil.UNIT|lst.PRODUCT(car.NAT*cdr.VAR(l))))
TypeT 1:
lst.(car.1,cdr.lst.(car.2,cdr.lst.(car.3,cdr.lst.(car.4,cdr.nil.unit))))
TypeT 2:
lst.(car.5,cdr.lst.(car.1,cdr.lst.(car.3,cdr.lst.(car.4,cdr.lst.(car.6,cdr.nil.unit)))))
Delta :
[0.55,0.55]
DeltaRec: [ins.5, cpy.1, del.2, cpy.3, cpy.4, dismatch: (nil-to-lst).Chg.(nil.unit,lst.(car.6,cdr.nil.unit))]
