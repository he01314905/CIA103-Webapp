package com.manager.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;
@WebServlet("/manager.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/back-end/images_upload"; // 上傳檔案的目的地目錄;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			
				String str = req.getParameter("managerNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				Integer managerNo = null;
				try {
					managerNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(managerNo);
				if (managerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("managerVO", managerVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/manager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
			Integer managerNo= Integer.valueOf(req.getParameter("managerNo"));
				
				/***************************2.開始查詢資料****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(managerNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("managerVO",managerVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/manager/update_manager_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer managerNo = Integer.valueOf(req.getParameter("managerNo").trim());
				
String managerName = req.getParameter("managerName");
				String managerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (managerName == null || managerName.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!managerName.trim().matches(managerNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String managerAccount = req.getParameter("managerAccount").trim();
				String managerAccountReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (managerAccount == null ||managerAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	else if(!managerAccount.trim().matches(managerAccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員帳號: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }

String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (managerPassword == null ||managerPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	else if(!managerPassword.trim().matches(managerPasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員密碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }

Integer managerstatus = Integer.valueOf(req.getParameter("managerstatus").trim());
				String managerstatusReg = "^[0-1]$";
				if (managerstatus == null ||managerstatus.toString().trim().length() == 0) {
					errorMsgs.add("管理員狀態請勿空白");
				}	else if(!managerstatus.toString().trim().matches(managerstatusReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員狀態: 只能是0或1 ,0為離職,1為在職 ");
	            }


				ManagerVO managerVO = new ManagerVO();
				managerVO.setManagerNo(managerNo);
				managerVO.setManagerName(managerName);
				managerVO.setManagerAccount(managerAccount);
				managerVO.setManagerPassword(managerPassword);
				managerVO.setManagerstatus(managerstatus);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/update_manager_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ManagerService managerSvc = new ManagerService();
				managerVO = managerSvc.updateManager(managerNo,managerName ,managerAccount ,managerPassword,managerstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("managerVO", managerVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/manager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
String managerName = req.getParameter("managerName");
				String managerNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (managerName == null || managerName.trim().length() == 0) {
					errorMsgs.add("管理員姓名: 請勿空白");
				} else if(!managerName.trim().matches(managerNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String managerAccount = req.getParameter("managerAccount").trim();
				String managerAccountReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (managerAccount == null ||managerAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	else if(!managerAccount.trim().matches(managerAccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員帳號: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }

String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (managerPassword == null ||managerPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	else if(!managerPassword.trim().matches(managerPasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員密碼: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }
				Integer managerstatus=1;
				 
				
				ManagerVO managerVO = new ManagerVO();
				
				managerVO.setManagerName(managerName);
				managerVO.setManagerAccount(managerAccount);
				managerVO.setManagerPassword(managerPassword);
				managerVO.setManagerstatus(managerstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/manager/addManager.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ManagerService managerSvc = new ManagerService();
				managerVO = managerSvc.addManager(managerName, managerAccount,managerPassword, managerstatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/manager/listAllManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer managerNo = Integer.valueOf(req.getParameter("managerNo"));
				
				/***************************2.開始刪除資料***************************************/
				ManagerService managerSvc = new ManagerService();
				managerSvc.deleteManager(managerNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/manager/listAllManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
//		res.setContentType("text/html; charset=Big5");
//		PrintWriter out = res.getWriter();
//		String realPath = getServletContext().getRealPath(saveDirectory);
//		File fsaveDirectory = new File(realPath);
//		if (!fsaveDirectory.exists())
//			 fsaveDirectory.mkdirs(); 
//		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
//		out.write("<h2> Total parts : " + parts.size() + "</h2>");
//
//		for (Part part : parts) {
//			String filename = part.getSubmittedFileName();
//			if (filename!= null && filename.length()!=0 && part.getContentType()!=null) {
//				out.println("<PRE>");
//				out.println("name: " + part.getName());
//				out.println("filename: " + filename);
//				out.println("ContentType: " + part.getContentType());
//				out.println("size: " + part.getSize());
//				
//				File f = new File(fsaveDirectory, filename);
//				out.println("File: " + f);
//
//				// 利用File物件,寫入目地目錄,上傳成功
//				part.write(f.toString());
//				out.println("<br><img src=\""+req.getContextPath()+saveDirectory+"/"+filename+"\">");
//
//				out.println();
//				out.println("</PRE>");
//			}
//		}
		
		if ("login".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
String managerAccount = req.getParameter("managerAccount");
				String managerAccountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (managerAccount == null || managerAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				} else if(!managerAccount.trim().matches(managerAccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String managerPassword = req.getParameter("managerPassword").trim();
				String managerPasswordReg = "^[(a-zA-Z0-9)]{2,10}$";
				if (managerPassword == null ||managerPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	else if(!managerPassword.trim().matches(managerPasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員帳號: 只能是英文字母、數字 , 且長度必需在2到10之間");
	            }
				ManagerVO managerVO = new ManagerVO();
				managerVO.setManagerAccount(managerAccount);
				managerVO.setManagerPassword(managerPassword);
				ManagerService managerSvc = new ManagerService();
				 managerVO = managerSvc.getAP(managerAccount,managerPassword);
				if(managerVO == null ) {
					 errorMsgs.add("帳號或密碼錯誤，請重新輸入。");
					 
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的empVO物件,也存入req
										RequestDispatcher failureView = req
												.getRequestDispatcher("/back-end/manager/Login.jsp");
										failureView.forward(req, res);
										return;
				}
				
				String url = "/back-end/manager/listAllManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				req.removeAttribute("errorMsgs");
				successView.forward(req, res);
				
	}
}
}

