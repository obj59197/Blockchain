
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Scanner;



public class Speakchain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 

	public static int difficulty = 1;
	
		public static void main(String[] args) throws IOException {
//			
//			blockchain.add(new Block("First Block Data", "0"));
//			System.out.println("Trying to Mine block 1... ");
//			blockchain.get(0).mineBlock(difficulty);
//			
//			
//			blockchain.add(new Block("Second Block Data",blockchain.get(blockchain.size()-1).hash));
//			System.out.println("Trying to Mine block 2... ");
//			blockchain.get(1).mineBlock(difficulty);
//			
//			
//			blockchain.add(new Block("Third Block Data",blockchain.get(blockchain.size()-1).hash));
//			System.out.println("Trying to Mine block 3... ");
//			blockchain.get(2).mineBlock(difficulty);
//			
//			System.out.println("\nBlockchain is Valid: " + isChainValid());
//			
//			String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//			System.out.println("\nThe block chain: ");
//			System.out.println(blockchainJson);
			
			
			
			String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
			String session = "Session " + d+".txt";
			

			File files = new File(("//home//pi//P//Blockchain"+session));
			
			
//			try{
				if (files.createNewFile()){
					System.out.println("File is created!");
				}else{
					System.out.println("File already exists.");
				}
//			}catch(Exception e) {
//				
//				System.out.println("Error Creating New File");
//				return;
//			}
//				Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-r--r--");
//				Files.setPosixFilePermissions(files.toPath(), 
//					    permissions);

			
			String md = "";
			String lastmd = "l";
			int block = 0;
			
			blockchain.add(new Block("Genesis Block", "0"));
			blockchain.get(0).mineBlock(difficulty);
			block++;
			System.out.println("Genesis Block Created.");
			
			
			while(block<5) {
				while( ((md = extract()).equalsIgnoreCase(lastmd)) ){
					
				}
					System.out.println("md is " + md);
		
					blockchain.add(new Block(md,blockchain.get(blockchain.size()-1).hash));
					blockchain.get(block).mineBlock(difficulty);
					System.out.println("Added Block # "+block);
					block++;
					lastmd = md;

			}//outer while
			
			String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
			blockchainJson+="\n";
			try {
				FileWriter writer = new FileWriter(files);
				writer.write(blockchainJson);
				writer.close();
				System.out.println("Session File Written");
				
			}catch(Exception e) {
				System.out.println("Error Writing to File");
			}
			
			System.out.println("Program Ended");
			
		} // end main
		

		
		public static String extract() {
			String md = "";
			String thisLine = "";
		      try {
		          
		          // open input stream test.txt for reading purpose.
		          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		        
		          while (true) {
		        	  		thisLine = br.readLine();
		        	  	//	br.skip();
		        	  		
		        	  		if(thisLine.length()>10 && thisLine.substring(0, 10).equalsIgnoreCase("Album Name")) {
		        	  			md = thisLine;
		        	  		}
		        	  		if(thisLine.length()>6 && thisLine.substring(0, 6).equalsIgnoreCase("Artist")) {
		        	  			md += "  " + thisLine;
		        	  		}
		        	  		if(thisLine.length()>5 && thisLine.substring(0, 5).equalsIgnoreCase("Title")) {
		        	  			md += "  " + thisLine;
		        	  			break;
		        	  		}
		          }
		          
	          } catch(Exception e) {
		           System.out.println("Extract Exception");
		          }

			return md; 
		}
		
		
		
		
		
		
		public static Boolean isChainValid() {
			Block currentBlock; 
			Block previousBlock;
			String hashTarget = new String(new char[difficulty]).replace('\0', '0');
			
			//loop through blockchain to check hashes:
			for(int i=1; i < blockchain.size(); i++) {
				currentBlock = blockchain.get(i);
				previousBlock = blockchain.get(i-1);
				//compare registered hash and calculated hash:
				if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
					System.out.println("Current Hashes not equal");			
					return false;
				}
				//compare previous hash and registered previous hash
				if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
					System.out.println("Previous Hashes not equal");
					return false;
				}
				//check if hash is solved
				if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
					System.out.println("This block hasn't been mined");
					return false;
				}
			}
			return true;
		}
		
		
	
	
	
	
	
}//end class
