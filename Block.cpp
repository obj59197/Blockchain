#include "Block.h"
#include "sha256.h"

Block::Block(uint32_t indexIn, const string &dataIn) : index(indexIn), data(dataIn){
  nonce = -1;
  time = time(nullptr);
}

string Block::getHash(){
  return hash;
}

void Block::mineBlock(uint32_t diff){
  char cstr[diff + 1];
  for(uint32_t i = 0; i < diff; ++i){
    cstr[i] = '0';
  }
  cstr[diff] = '\0';
  string str(cstr);
  
  do{
    nonce++;
    hash = calculateHash();
  }while(hash.substr(0, diff) != str);
  
  cout << "Block mined: " << hash << endl; 
}

inline string Block::calculateHash() const{
  stringstream ss;
  ss << index << time << data << nonce << prevHash;
  return sha256(ss.str());
}
