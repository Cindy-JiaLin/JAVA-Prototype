
TYPE: PRODUCT(fst.NAT*snd.CHAR*trd.PRODUCT(fst.UNIT*snd.NAT)*fth.UNION(left.LIST(CHAR)|right.LIST(NAT)))
TypeT 1:
(fst.1,snd.A,trd.(fst.unit,snd.2),fth.left.[b,c,d])
TypeT 2:
(fst.3,snd.c,trd.(fst.unit,snd.2),fth.left.[a,c,d,e])
Delta :
DeltaProduct:(Chg.(1,3), Chg.(A,c), DeltaProduct:(cpy.unit, cpy.2), left.[0.0,0.0]
DeltaList: [ins.a, del.b, cpy.c, cpy.d, ins.e])
