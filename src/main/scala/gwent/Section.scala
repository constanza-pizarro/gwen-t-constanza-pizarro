package cl.uchile.dcc
package gwent

import gwent.cards.Card

class Section(var closeCombatZone: List[Card] = List(),
              var rangedCombatZone: List[Card] = List(),
              var siegeCombatZone: List[Card] = List())
