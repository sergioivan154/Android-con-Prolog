package com.example.prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import alice.tuprolog.InvalidTheoryException;
import alice.tuprolog.MalformedGoalException;
import alice.tuprolog.NoSolutionException;
import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;
import alice.tuprolog.UnknownVarException;
import android.content.Context;
import android.util.Log;

public class Motor{
	
	public String calcular(Context cont)
	{
		String res = "Resultado no encontrado";
		    	try
		    	{
		    	    OutputStreamWriter fout=
		    	        new OutputStreamWriter(
		    	        		cont.openFileOutput("familia.pl", Context.MODE_PRIVATE));
		    	 //Creas el archivo de prolog en el dispositivo móvil si no hay alternativa pero depende del problema seguro la hay
		    	    fout.write(			
		    	    		"esMadreDe(sara, jorge)."+
							" esMadreDe(sara, gonzalo)."+
							" esMadreDe(sara, lucia)."+
							" esMadreDe(lucia, jose)."+
							" esMadreDe(lucha, martin)."+
							" esMadreDe(daniela, ana)."+
							" esMadreDe(daniela, miguel)."+
							" esMadreDe(carmen, dora)."+
							" esMadreDe(carmen, enrique)."+
							" esPadreDe(manuel, gonzalo)."+
							" esPadreDe(manuel, lucia)."+
							" esPadreDe(manuel, fernando)."+
							" esPadreDe(jorge, martin)."+
							" esPadreDe(jorge, francisco)."+
							" esPadreDe(gonzalo, ana)."+
							" esPadreDe(gonzalo, miguel)."+
							" esPadreDe(fernando, dora)."+
							" esPadreDe(fernando, enrique)."+
							" esAbuelaDe(A,B) :-"+
							" esMadreDe(A, N),"+
							" esMadreDe(N, B)."+
							" esAbuelaDe(A,B) :-"+
							" esPadreDe(A, N),"+
							" esPadreDe(N, B)."+
							" esAbuelaDe(A,B) :-"+
							" esPadreDe(A, N),"+
							" esMadreDe(N, B)."+
							" esAbuelaDe(A,B) :-"+
							" esMadreDe(A, N),"+
							" esPadreDe(N, B)."+
							" esNietoDe(A, B) :-"+
							" esAbuelaDe(B, A)."+
							" esHermanoDe(A, B) :-"+
							" esMadreDe(N, A),"+
							" esMadreDe(N, B),"+
							" not(A=B)."+
							" esHermanoDe(A, B) :-"+
							" esPadreDe(N, A),"+
							" esPadreDe(N, B),"+
							" not(A=B)."+
							" esSobrinoDe(A, B) :-"+
							" esHermanoDe(N, A),"+
							" esMadreDe(N, B)."+
							" esSobrinoDe(A, B) :-"+
							" esHermanoDe(N, A),"+
							" esPadreDe(N, B)."+
							" esTioDe(A, B) :-"+
							" esSobrinoDe(B, A)."+
							" esPrimoDe(A, B) :-"+
							" esTioDe(A, N),"+
							" esMadreDe(N, B),"+
							" not(A=B)."+
							" esPrimoDe(A, B) :-"+
							" esTioDe(A, N),"+
							" esPadreDe(N, B),"+
							" not(A=B)."
							);
		    	    fout.close();
		    	    //Prolog
		    	    //La implementacion de prolog viene de esta linea hacia abajo
		    	    Prolog p=new Prolog();
		    	    File f=new File("data/data/com.example.prueba/files/familia.pl");
		    	    if(f.exists())
		    	    {
		    	    try 
		    	    {
		    	    Theory t=new Theory(new FileInputStream(f));
		    	    p.setTheory(t);
		    	    SolveInfo answer=p.solve("esAbuelaDe(sara,B).");
		    	    //getTerm se refiere a que si preguntas algo que te de por ejemplo familiar(sergio,HIJO,ivan,TIO). si usas getTerm tienes que poner getTerm("hijo") y otro getTerm para ("Tio")
		    	    Term derivate=answer.getTerm("B");
		    	    res=derivate.toString();
		    	    } catch (IOException e) {
		    	    // TODO Auto-generated catch block
		    	    e.printStackTrace();
		    	    } catch (InvalidTheoryException e) {
		    	    // TODO Auto-generated catch block
		    	    e.printStackTrace();
		    	    } catch (MalformedGoalException e) {
		    	    // TODO Auto-generated catch block
		    	    e.printStackTrace();
		    	    } catch (NoSolutionException e) {
		    	    // TODO Auto-generated catch block
		    	    e.printStackTrace();
		    	    } catch (UnknownVarException e) {
		    	    // TODO Auto-generated catch block
		    	    e.printStackTrace();
		    	    }
		    	    }
		    	   
		    	}
		    	catch (Exception ex)
		    	{
		    	    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
		    	}
		    	
		    return res;
		    }
}

