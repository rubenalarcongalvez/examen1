package es.iesvegademijas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import es.iesvegademijas.streams.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

class EmpleadosStreamTests {

	@Test
	void testSkeletonDepartamento() {
	
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			
			
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonEmpleado() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listProd = empHome.findAll();		
						
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	@Test
	void testAllDepartamento() {
	
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllEmpleado() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();		
			listEmp.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 1. Lista el código de los departamentos de los empleados, 
	 * eliminando los códigos que aparecen repetidos.
	 */
	@Test
	void test1() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var codEmpl = listEmp.stream()
								 .filter(d -> d.getDepartamento() != null)
								 .map(d -> d.getDepartamento().getCodigo())
								 .distinct()
								 .collect(toList());
			
			
			codEmpl.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 2. Lista el nombre y apellidos de los empleados en una única columna, convirtiendo todos los caracteres en mayúscula 
	 * 
	 */
	@Test
	void test2() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			
			var listEmpl = listEmp.stream()
							 .map(e -> {
								 String resultado = "Nombre: " + e.getNombre() + "\nApellidos: " + e.getApellido1();
								 
								 if (e.getApellido2() != null) {
									 resultado += " " + e.getApellido2();
								 }
								 
								 return resultado.toUpperCase();
							 })
							 .collect(toList());
			
			listEmpl.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}

	/**
	 * 3. Lista el código de los empleados junto al nif, pero el nif deberá aparecer en dos columnas, 
	 * una mostrará únicamente los dígitos del nif y la otra la letra.
	 */
	
	@Test
	void test3() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var lista = listEmp.stream()
							   .map(e -> {
								   String numeros = e.getNif().substring(0, e.getNif().length() - 2);
								   String letra = e.getNif().substring(e.getNif().length() - 1);
								   
								   String resultado = "Código: " + e.getCodigo() + "\nNúmeros del NIF: " + numeros + ", Letra: " + letra;
								   
								   return resultado;
							   }).collect(toList());
			
			
			lista.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 4. Lista el nombre de cada departamento y el valor del presupuesto actual del que dispone. 
	 * Para calcular este dato tendrá que restar al valor del presupuesto inicial (columna presupuesto) los gastos que se han generado (columna gastos).
	 *  Tenga en cuenta que en algunos casos pueden existir valores negativos.
	 */
	@Test
	void test4() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var departamentos = listDep.stream()
									   .map(d -> "Nombre: " + d.getNombre() + ", Presupuesto actual: " + (d.getPresupuesto() - d.getGastos()))
									   .collect(toList());
			
			departamentos.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el nombre de los departamentos y el valor del presupuesto actual ordenado de forma ascendente.
	 */
	@Test
	void test5() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var departamentos = listDep.stream()
									   .sorted((a, b) -> Double.compare(a.getPresupuesto() - a.getGastos(), b.getPresupuesto() - b.getGastos()))
									   .map(d -> "Nombre: " + d.getNombre() + ", Presupuesto actual: " + (d.getPresupuesto() - d.getGastos()))
									   .collect(toList());
			
			departamentos.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Devuelve una lista con el nombre y el presupuesto, de los 3 departamentos que tienen mayor presupuesto
	 */
	@Test
	void test6() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
							   .sorted(comparing(Departamento::getPresupuesto).reversed())
							   .limit(3)
							   .map(d -> "Nombre: " + d.getNombre() + ", Presupuesto: " + d.getPresupuesto())
							   .collect(toList());
			
			lista.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Devuelve una lista con el nombre de los departamentos y el presupesto, de aquellos que tienen un presupuesto entre 100000 y 200000 euros
	 */
	@Test
	void test7() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
					   .filter(p -> p.getPresupuesto() >= 100000 && p.getPresupuesto() <= 200000)
					   .map(d -> "Nombre: " + d.getNombre() + ", Presupuesto: " + d.getPresupuesto())
					   .collect(toList());
	
			lista.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 8. Devuelve una lista con 5 filas a partir de la tercera fila de empleado ordenado por código de empleado. La tercera fila se debe incluir en la respuesta.
	 */
	@Test
	void test8() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var lista = listEmp.stream()
					   		   .skip(2)
					   		   .limit(5)
					   		   .sorted(comparing(Empleado::getCodigo))
					   		   .collect(toList());
	
			lista.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 9. Devuelve una lista con el nombre de los departamentos y el gasto, de aquellos que tienen menos de 5000 euros de gastos.
	 * Ordenada de mayor a menor gasto.
	 */
	@Test
	void test9() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
					   .filter(p -> p.getGastos() < 5000)
					   .sorted(comparing(Departamento::getGastos).reversed())
					   .map(d -> "Nombre: " + d.getNombre() + ", Gasto: " + d.getGastos())
					   .collect(toList());
	
			lista.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista todos los datos de los empleados cuyo segundo apellido sea Díaz o Moreno
	 */
	@Test
	void test10() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var lista = listEmp.stream()
					   		   .filter(e -> e.getApellido2() != null)
							   .filter(e -> (e.getApellido2().equalsIgnoreCase("Díaz") || e.getApellido2().equalsIgnoreCase("Moreno")))
					   		   .collect(toList());
	
			lista.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 11. Lista los nombres, apellidos y nif de los empleados que trabajan en los departamentos 2, 4 o 5
	 */
	@Test
	void test11() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var lista = listEmp.stream()
					   		   .filter(d -> d.getDepartamento() != null &&
					   				   (d.getDepartamento().getCodigo() == 2
					   				   || d.getDepartamento().getCodigo() == 4
					   				|| d.getDepartamento().getCodigo() == 5
					   				   )	
					   				  )
					   		   .map(e -> {
					   			   String apellido2 = "";
					   			   
					   			   if (e.getApellido2() != null) {
					   				   apellido2 = " " + e.getApellido2();
					   			   }
					   			   
					   			   String resultado = "Nombre: " + e.getNombre() + ", Apellidos: " + e.getApellido1() + apellido2 + "NIF: " + e.getNif();
					   			   
					   			   return resultado;
					   		   })
					   		   .collect(toList());
	
			lista.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	
	/**
	 * 12. Devuelve el nombre del departamento donde trabaja el empleado que tiene el nif 38382980M
	 */
	@Test
	void test12() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var lista = listEmp.stream()
					   		   .filter(e -> e.getNif().equalsIgnoreCase("38382980M"))
					   		   .map(d -> "Departamento: " + d.getDepartamento().getNombre())
					   		   .collect(joining());
	
			System.out.println(lista);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 13. Devuelve una lista con el nombre y los apellidos de los empleados que tienen los departamentos 
	 * que no tienen un presupuesto entre 100000 y 200000 euros.
	 */
	@Test
	void test13() {
	
		EmpleadoHome empHome = new EmpleadoHome();	
		try {
			empHome.beginTransaction();
		
			List<Empleado> listEmp = empHome.findAll();	
			
			var lista = listEmp.stream()
			   		   		   .filter(d -> d.getDepartamento() != null)
							   .filter(d -> d.getDepartamento().getPresupuesto() <= 100000 || d.getDepartamento().getPresupuesto() >= 200000)
				   		   	   .map(e -> {
									 String resultado = "Nombre: " + e.getNombre() + "\nApellidos: " + e.getApellido1();
									 
									 if (e.getApellido2() != null) {
										 resultado += " " + e.getApellido2();
									 }
									 
									 return resultado;
								 })
				   		   	   .collect(toList());
			
			lista.forEach(System.out::println);
		
			empHome.commitTransaction();
		}
		catch (RuntimeException e) {
			empHome.rollbackTransaction();
		    throw e; // or display error message
		}	
	
	}
	
	/**
	 * 14. Calcula el valor mínimo del presupuesto de todos los departamentos.
	 */
	@Test
	void test14() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
							   .map(Departamento::getPresupuesto)
							   .reduce(Double::min);
	
	   		System.out.println(lista);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 15. Calcula el número de empleados que hay en cada departamento. 
	 * Tienes que devolver dos columnas, una con el nombre del departamento 
	 * y otra con el número de empleados que tiene asignados.
	 */
	@Test
	void test15() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
					   		   .map(e -> {
					   			   String empleados = "Departamento: " + e.getNombre();
					   			   
					   			   if (e.getEmpleados() != null) {
					   				   empleados += ", Número de empleados: " + e.getEmpleados().parallelStream().count();
					   			   }
					   			   
					   			   return empleados;
					   		   })
					   		   .collect(toList());

			lista.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 16. Calcula el número total de empleados que trabajan en cada unos de los departamentos que tienen un presupuesto mayor a 200000 euros.
	 */
	@Test
	void test16() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
			   		   		   .filter(e -> e.getEmpleados() != null)
			   		   		   .filter(d -> d.getPresupuesto() > 200000)
			   		   		   .map(e -> e.getEmpleados().stream().count())
			   		   		   .collect(toList());

			lista.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 17. Calcula el nombre de los departamentos que tienen más de 2 empleados. 
	 * El resultado debe tener dos columnas, una con el nombre del departamento y
	 *  otra con el número de empleados que tiene asignados
	 */
	void test17() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			var lista = listDep.stream()
			   		   		   .

			   		   		   //TODO Voy a intentar hacer anotaciones al menos plantear...
			   		   		   
			lista.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/** 18. Lista todos los nombres de departamentos junto con los nombres y apellidos de los empleados. 
	 * 
	 */
	void test18() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 19. Devuelve la media de empleados que trabajan en los departamentos
	 */
	void test19() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 20. Calcula el presupuesto máximo, mínimo  y número total de departamentos con un solo stream.
	 */
	void test20() {
		
		DepartamentoHome depHome = new DepartamentoHome();
		
		try {
			depHome.beginTransaction();
	
			List<Departamento> listDep = depHome.findAll();
			
			//
			
			listDep.forEach(System.out::println);
		
			depHome.commitTransaction();
		}
		catch (RuntimeException e) {
			depHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
}
