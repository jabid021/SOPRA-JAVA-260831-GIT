package view;

public class Views {

	public class Common{}
		public class Produit extends Common{}
			public class ProduitWithVentes extends Produit{}
		
			
		public class Client extends Common{}
			public class ClientWithAchats extends Client{}
	
			
		
		public class Fournisseur extends Common{}
			public class FournisseurWithStock extends Fournisseur{}
	
}
