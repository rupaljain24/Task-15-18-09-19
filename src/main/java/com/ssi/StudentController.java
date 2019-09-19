package com.ssi;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	
	@RequestMapping("newStudent")
	public String AddStudent(@ModelAttribute("info") StudentModel model){
		
		Configuration config= new Configuration().configure();
		SessionFactory sf=config.buildSessionFactory();
		Session session=sf.openSession();
		

		org.hibernate.Transaction tr=session.beginTransaction();
		
//		String sql ="insert into student values(?,?,?,?) ";
//		SQLQuery query = session.createSQLQuery(sql);
//		query.setParameter(1,model.getId());
//		query.setParameter(2,model.getName());
//		query.setParameter(3,model.getPhno());
//		query.setParameter(4,model.getMarks());
//		
//		query.executeUpdate();
//		
//		
		
		session.save(model);
		tr.commit();
		session.close();
		System.out.println("Data Added");
		
		
		return "index.jsp";
	}
	
	
	@RequestMapping("findStudent")
	public  ModelAndView SearchStudent(@RequestParam("id") int rollno){
	Configuration config= new Configuration().configure();
	SessionFactory sf=config.buildSessionFactory();
	Session session=sf.openSession();
	
//		String sql =" select * from student where id=? ";
//		
//		SQLQuery query = session.createSQLQuery(sql);
//		query.setParameter(1, rollno);
//		//query.addEntity(StudentModel.class);
		StudentModel s=new StudentModel();
		
		ModelAndView mv=new ModelAndView("SearchStudent.jsp");
//		
//		List<Object[]> list = query.list();
//		for(Object objects[]:list)
//		 {
//			System.out.println(objects[0]+"  "+objects[1]+" "+objects[2]+" "+objects[3]);
//			s.setId(Integer.parseInt(objects[0].toString()));
//			s.setName((objects[1].toString()));
//			s.setPhno((objects[2].toString()));
//			s.setMarks(Integer.parseInt(objects[3].toString()));
//			
//		 }
		s=session.get(StudentModel.class, rollno);
		System.out.println(s);
		mv.addObject("info", s);
		return mv;
	}
	
	
	@RequestMapping("AllStudent")
	public  ModelAndView AllStudent(){
	Configuration config= new Configuration().configure();
	SessionFactory sf=config.buildSessionFactory();
	Session session=sf.openSession();
	
		String sql =" select * from student ";
		
		SQLQuery query = session.createSQLQuery(sql);
		//query.addEntity(StudentModel.class);
		
		
		ModelAndView mv=new ModelAndView("AllStudent.jsp");
		
		List<Object[]> list = query.list();
		List<StudentModel> listStudent = new ArrayList<StudentModel>(); 
		for(Object objects[]:list)
		 {StudentModel s=new StudentModel();
			System.out.println(objects[0]+"  "+objects[1]+" "+objects[2]+" "+objects[3]);
			s.setId(Integer.parseInt(objects[0].toString()));
			s.setName((objects[1].toString()));
			s.setPhno((objects[2].toString()));
			s.setMarks(Integer.parseInt(objects[3].toString()));
			listStudent.add(s);
			
		 }
		
		mv.addObject("infoAll", listStudent);
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}