package cl.uchile.dcc
package gwent

class CardTest extends munit.FunSuite {
  var card1 = new UnitCard("carta1","asedio","a",20);
  var card2 = new UnitCard("carta2","cc","a",20);
  var card3 = new UnitCard("carta1","asedio","a",21);
  var card4 = new UnitCard("carta1","asedio","a",20);

  var card5 = new WeatherCard("carta5", "a");
  var card6 = new WeatherCard("carta6", "a");
  var card7 = new WeatherCard("carta5", "e");
  var card8 = new WeatherCard("carta5", "a");

  test("unitEquals") {
    assertEquals(new UnitCard("carta", "cc","a",2),
                 new UnitCard("carta", "cc","a",2))
    assertEquals(card1,card1);
    assertEquals(card1, new UnitCard("carta1","asedio","a",20));
    assert(!card1.equals(card2));
    assert(!card1.equals(card3));
    assertEquals(card1,card4);
    assertEquals(card4,card1);
  }

  test("weatherEquals") {
    assertEquals(new WeatherCard("carta","a"),
                 new WeatherCard("carta","a"))
    assertEquals(card5, card5);
    assertEquals(card5, new WeatherCard("carta5", "a"));
    assert(!card5.equals(card6));
    assert(!card5.equals(card7));
    assertEquals(card5, card8);
    assertEquals(card8, card5);
  }
}
