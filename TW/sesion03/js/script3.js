      //Declaracion de variables
	        var num1 = 0;
	        var num2 = 0;
	        var opera;

        //Función que concatena el número presionado. Luego refresca el texto
        function darNumero(numero){
            //IA: obtener el valor de id valor_numero
            var valor = document.getElementById("valor_numero").value;
            if (valor == "0") {
                valor = numero;
            } else {
                valor += numero;
            }
            document.getElementById("valor_numero").value = valor;
        }

        //Función que coloca la coma al presionar dicho botón. Luego refresca el texto
        function darComa(){
            var valor = document.getElementById("valor_numero").value;
            if (valor.indexOf(".") == -1) {
                valor += ".";
            }
            document.getElementById("valor_numero").value = valor;
        }

        //Función que coloca la C al presionar dicho botón. Luego refresca el texto
        function darC(){
            document.getElementById("valor_numero").value = "0";
            num1 = 0;
            num2 = 0;
            opera = "";
        }


        //Esta función realiza las distintas operaciones aritméticas en función del botón pulsado
        function operar(valor){
            num1 = parseFloat(document.getElementById("valor_numero").value);
            opera = valor;
            document.getElementById("valor_numero").value = "0";
        }

        //Función para pulsar igual. Al final llama a refrescar()
            /*
        	suma = 1
        	resta = 2
        	multiplicacion = 3
        	division = 4
        	potencia = 5
        */

        function esIgual(){
            num2 = parseFloat(document.getElementById("valor_numero").value);
            var resultado = 0;
            switch (opera) {
                case 1: 
                    resultado = num1 + num2;
                    break;
                case 2: 
                    resultado = num1 - num2;
                    break;
                case 3:
                    resultado = num1 * num2;
                    break;
                case 4:
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        alert("No se puede dividir por cero");
                        resultado = num1;
                    }
                    break;
                case 5:
                    resultado = Math.pow(num1, num2);
                    break;
            }
            document.getElementById("valor_numero").value = resultado;
        }
        //Muestra en el cuadro de texto el valor
        function refrescar(){
            document.getElementById("valor_numero").value = num1;
        }
