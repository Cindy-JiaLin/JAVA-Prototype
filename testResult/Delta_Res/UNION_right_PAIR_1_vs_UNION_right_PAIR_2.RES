
TYPE: UNION(left.NAT|right.PRODUCT(fst.CHAR*snd.BOOL))
TypeT 1:
right.(fst.e,snd.true)
TypeT 2:
right.(fst.e,snd.false)
Delta :
right.DeltaProduct:(fst.cpy.e, snd.Chg.(true,false))
