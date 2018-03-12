#include "Blockchain.h"

Blockchain::Blockchain(){
  chain.emplace_back(Block(0, "Genesis Block"));
  diff = 6;
}

void Blockchain::addBlock(Block new){
  new.prevHash = getLastBlock().getHash();
  new.mineBlock(diff);
  chain.push_back(new);
}

Block Blockchain::getLastBlock() const{
  return chain.back();
}
