#include <cstdint>
#include <vector>
#include "Block.h"

using namespace std;

class Blockchain{
  public:
    Blockchain();
    void addBlock(Block new);
    
  private:
    uint32_t diff;
    vector<Block> chain;
    
    Block getLastBlock() const;
};
