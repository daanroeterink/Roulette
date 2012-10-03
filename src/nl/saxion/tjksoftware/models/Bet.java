package nl.saxion.tjksoftware.models;

public class Bet {
	
	private Player player;
	private BetLocation betLocation;
	private int betAmmount;
	
	public enum BetLocation {zero,one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve,
		thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen,twenty,twenty_one,twenty_two,
		twenty_three,twenty_four,twenty_five,twenty_six,twenty_seven,twenty_eigth,twenty_nine,thirty,
		thirty_one,thirty_two,thirty_three,thirty_four,thirty_five,thirty_six,first_two_to_one,second_two_to_one,
		third_two_to_one,first_twelve,second_twelve,third_twelve,one_to_eighteen,even,red,black,odd,nineteen_to_thirty_six};
		
		public Bet(Player playerBet, int ammount, BetLocation betlocation) {
			player = playerBet;
			betAmmount = ammount;
			betLocation = betlocation;
		}
				
		
}
