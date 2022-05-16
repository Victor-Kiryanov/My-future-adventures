function InsertOperators() {
			var PIBs = document.getElementById("PIBf").value;
			var accessIdentifierS = document.getElementById("accessIdentifierF").value;
	
			fetch('http://localhost:8080/MyFutureAdventure/OperatorsServlet',{
				method: 'POST',
				body: JSON.stringify({
					PIBs,
					accessIdentifierS,
					status: 'INSERT',
				}),
				headers: {
				    'Content-type': 'application/json; charset=UTF-8',
				},
			});
		}

function DeleteOperators(){
			var id = document.getElementById("idOper").value;
	
			fetch('http://localhost:8080/MyFutureAdventure/OperatorsServlet',{
				method: 'DELETE',
				body: JSON.stringify({
					id,
					status: 'DELETE',
				}),
				headers: {
				    'Content-type': 'application/json; charset=UTF-8',
				},
			});
		}
		
function UpdateOperators(){
			var id = document.getElementById("idO").value;
			var accessIdentifier = document.getElementById("accessIdentifierUpdate").value;
	
			fetch('http://localhost:8080/MyFutureAdventure/OperatorsServlet',{
				method: 'PUT',
				body: JSON.stringify({
					id, accessIdentifier,
					status: 'UPDATE',
				}),
				headers: {
				    'Content-type': 'application/json; charset=UTF-8',
				},
			});
		}
		
function DeleteClients(){
	var id = document.getElementById("idCL").value;
	
			fetch('http://localhost:8080/MyFutureAdventure/ClientServlet',{
				method: 'DELETE',
				body: JSON.stringify({
					id,
					status: 'DELETE',
				}),
				headers: {
				    'Content-type': 'application/json; charset=UTF-8',
				},
			});
}

function ConfirmRequest(idQ, idClientQ){
	fetch('http://localhost:8080/MyFutureAdventure/QueueServlet',{
				method: 'PUT',
				body: JSON.stringify({
					idQ, idClientQ,
					status: 'UPDATE',
				}),
				headers: {
				    'Content-type': 'application/json; charset=UTF-8',
				},
			});
}
		