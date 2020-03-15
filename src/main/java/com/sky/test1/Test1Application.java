package com.sky.test1;

import com.sky.test1.controller.Test1Controller;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* Application to invoke python script from Java
* */
@SpringBootApplication
public class Test1Application extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	private static Process p = null;

	public static void main(String[] args) {
		SpringApplication.run(Test1Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Test1Application.class);
	}

	/*@PostConstruct
	public void init() throws IOException {
		logger.info("Test1Application - Inside Post Construct");
		System.out.println("Inside post construct \n Application started, now invoking python script");
		logger.info("Test1Application - Application started, now invoking python script.......");
		if(p == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("api.py").getFile());
			String fileName = file.getAbsolutePath();
			logger.info("Test1Application - "+fileName);

			String[] cmd = {
					"/bin/bash",
					"-c",
					"python3 "+ fileName
			};
			p = Runtime.getRuntime().exec(cmd);
			//p.waitFor();
		}
		else
			logger.info("TestApplication Python process already running...");

		logger.info("Test1Application - After invoking python script.......\n Exiting Post Construct");
	}*/

	/*@PostConstruct
	public void init() throws IOException, InterruptedException {
		logger.info("Test1Application - Inside Post Construct");
		System.out.println("Inside post construct \n Application started, now invoking python script");
		logger.info("Test1Application - Application started, now invoking python script.......");
		if(p == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			*//*File file = new File(classLoader.getResource("api.py").getFile());
			String fileName = file.getAbsolutePath();
			logger.info("Test1Application - "+fileName);*//*

			File file2 = new File(classLoader.getResource("perm.sh").getFile());
			File parentDir = file2.getParentFile();
			System.out.println(parentDir);
			String initFileName = file2.getAbsolutePath();
			logger.info("Test1Application - "+initFileName);

			String[] cmd = {
					"/bin/bash",
					"-c",
					"cd "+parentDir,
					"./ "+initFileName
			};

			//String cmd = "python3 "+initFileName;
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			String line;
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
		}
		else
			logger.info("TestApplication Python process already running...");

		logger.info("Test1Application - After invoking python script.......\n Exiting Post Construct");
	}*/

	/*@PostConstruct
	public void init() throws IOException, InterruptedException {
		logger.info("Test1Application - Inside Post Construct");
		System.out.println("Inside post construct \n Application started, now invoking python script");
		logger.info("Test1Application - Application started, now invoking python script.......");
		if(p == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			*//*File file = new File(classLoader.getResource("api.py").getFile());
			String fileName = file.getAbsolutePath();
			logger.info("Test1Application - "+fileName);*//*

			File file2 = new File(classLoader.getResource("api.py").getFile());
			File parentDir = file2.getParentFile();
			System.out.println(parentDir);
			String initFileName = file2.getAbsolutePath();
			logger.info("Test1Application - "+initFileName);

			ProcessBuilder processBuilder = new ProcessBuilder();
			//processBuilder.command("bash", "-c", "ls /Users/sky/");
			processBuilder.command("bash", "-c", "python3 "+initFileName);

			try {

				Process process = processBuilder.start();

				StringBuilder output = new StringBuilder();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(process.getInputStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					output.append(line + "\n");
				}

				int exitVal = process.waitFor();
				if (exitVal == 0) {
					System.out.println("Success!");
					System.out.println(output);
					System.exit(0);
				} else {
					System.out.println("abnormal......");
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else
			logger.info("TestApplication Python process already running...");

		logger.info("Test1Application - After invoking python script.......\n Exiting Post Construct");
	}*/

	@PostConstruct
	public void init() {
		logger.info("Test1Application - Inside Post Construct");
		System.out.println("Inside post construct \n Application started, now invoking python script");
		logger.info("Test1Application - Application started, now invoking python script.......");
		if(p == null) {
			ClassLoader classLoader = getClass().getClassLoader();
			/*File file = new File(classLoader.getResource("api.py").getFile());
			String fileName = file.getAbsolutePath();
			logger.info("Test1Application - "+fileName);*/

			File file2 = new File(classLoader.getResource("perm.sh").getFile());
			File parentDir = file2.getParentFile();
			System.out.println(parentDir);
			String initFileName = file2.getAbsolutePath();
			logger.info("Test1Application - "+initFileName);
			
			String sCommandString = "sh "+initFileName;
			CommandLine oCmdLine = CommandLine.parse(sCommandString);
			DefaultExecutor oDefaultExecutor = new DefaultExecutor();
			oDefaultExecutor.setExitValue(0);

			Thread commandLineThread = new Thread(() -> {
				try {
					int iExitValue = oDefaultExecutor.execute(oCmdLine);
					System.out.println("-------" + iExitValue);
				} catch (ExecuteException e) {
					System.err.println("Execution failed.");
					e.printStackTrace();
				} catch (IOException e) {
					System.err.println("permission denied.");
					e.printStackTrace();
				}
			});
			commandLineThread.setDaemon(true);
			commandLineThread.start();

		}
		else
			logger.info("TestApplication Python process already running...");

		logger.info("Test1Application - After invoking python script.......\n Exiting Post Construct");
	}
}
