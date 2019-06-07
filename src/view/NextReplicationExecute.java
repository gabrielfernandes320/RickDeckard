package view;

public class NextReplicationExecute {

	private NextWindowReplication io_window;
	
	public NextReplicationExecute(NextWindowReplication window) {
		
		// Guardo a referencia.
		io_window = window;
		
		// Guardo a conexão.
		
		// Instancia os DAO de controle.
	}
	
	public void ReplicacaoIniciar() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				while (!Thread.currentThread().isInterrupted() ) {
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		}).start();
		
	}
	
}
