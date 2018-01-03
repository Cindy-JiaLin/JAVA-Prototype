
TYPE: SET(PRODUCT(node.STRING*link.SET(PRODUCT(distance.REAL(0.5)*node.STRING))))
TypeT 1:
{(node.Baile Átha Cliath,link.{(distance.165.6(0.5),node.Béal Feirste),(distance.208.6(0.5),node.Gaillimh),(distance.233.3(0.5),node.Doire),(distance.255.3(0.5),node.Corcaigh)}),(node.Gaillimh,link.{(distance.208.6(0.5),node.Baile Átha Cliath),(distance.204.0(0.5),node.Corcaigh)}),(node.Corcaigh,link.{(distance.204.0(0.5),node.Gaillimh),(distance.255.3(0.5),node.Baile Átha Cliath)}),(node.Béal Feirste,link.{(distance.113.0(0.5),node.Doire),(distance.165.6(0.5),node.Baile Átha Cliath)}),(node.Doire,link.{(distance.113.0(0.5),node.Béal Feirste),(distance.233.3(0.5),node.Baile Átha Cliath)})}
TypeT 2:
{(node.Dublin,link.{(distance.82.0(0.5),node.Dundalk),(distance.233.3(0.5),node.Derry),(distance.209.0(0.5),node.Galway),(distance.255.0(0.5),node.Cork)}),(node.Dundalk,link.{(distance.82.0(0.5),node.Dublin),(distance.85.0(0.5),node.Belfast)}),(node.Galway,link.{(distance.204.0(0.5),node.Cork),(distance.209.0(0.5),node.Dublin)}),(node.Cork,link.{(distance.204.0(0.5),node.Galway),(distance.255.0(0.5),node.Dublin)}),(node.Belfast,link.{(distance.113.0(0.5),node.Derry),(distance.85.0(0.5),node.Dundalk)}),(node.Derry,link.{(distance.113.0(0.5),node.Belfast),(distance.233.3(0.5),node.Dublin)})}
Delta :
DeltaSet: [DeltaProduct:(node.Chg.(Doire,Derry), 
                         link.DeltaSet: [DeltaProduct:(distance.Chg.(233.3(0.5),233.3(0.5)), node.Chg.(Baile Átha Cliath,Dublin)), 
                                         DeltaProduct:(distance.Chg.(113.0(0.5),113.0(0.5)), node.Chg.(Béal Feirste,Belfast)), 
                                        ]
                        ), 
           DeltaProduct:(node.Chg.(Corcaigh,Cork), 
                         link.DeltaSet: [DeltaProduct:(distance.Chg.(255.3(0.5),255.0(0.5)), node.Chg.(Baile Átha Cliath,Dublin)), 
                                         DeltaProduct:(distance.Chg.(204.0(0.5),204.0(0.5)), node.Chg.(Gaillimh,Galway)), 
                                        ]
                        ), 
           DeltaProduct:(node.Chg.(Béal Feirste,Belfast), 
                         link.DeltaSet: [DeltaProduct:(distance.Chg.(165.6(0.5),85.0(0.5)), node.Chg.(Baile Átha Cliath,Dundalk)), 
                                         DeltaProduct:(distance.Chg.(113.0(0.5),113.0(0.5)), node.Chg.(Doire,Derry)), 
                                        ]
                        ), 
           DeltaProduct:(node.Chg.(Gaillimh,Galway), 
                         link.DeltaSet: [DeltaProduct:(distance.Chg.(204.0(0.5),204.0(0.5)), node.Chg.(Corcaigh,Cork)), 
                                         DeltaProduct:(distance.Chg.(208.6(0.5),209.0(0.5)), node.Chg.(Baile Átha Cliath,Dublin)), 
                                        ]
                        ), 
           DeltaProduct:(node.Chg.(Baile Átha Cliath,Dublin), 
                         link.DeltaSet: [DeltaProduct:(distance.Chg.(165.6(0.5),82.0(0.5)), node.Chg.(Béal Feirste,Dundalk)), 
                                         DeltaProduct:(distance.Chg.(255.3(0.5),255.0(0.5)), node.Chg.(Corcaigh,Cork)), 
                                         DeltaProduct:(distance.Chg.(208.6(0.5),209.0(0.5)), node.Chg.(Gaillimh,Galway)), 
                                         DeltaProduct:(distance.Chg.(233.3(0.5),233.3(0.5)), node.Chg.(Doire,Derry)), 
                                        ]
                        ), 
          ins.(node.Dundalk,link.{(distance.82.0(0.5),node.Dublin),(distance.85.0(0.5),node.Belfast)}), 
         ]
