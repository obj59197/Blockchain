#include <cstdint>
#include <iostream>

using namespace std;

class Block{
  
  public:
    string strPrevious;
    Block(uint32_t indexIn, const string &dataIn);
    string getHash();
    void mineBlock(uint32_t diff);
  
  private:
    uint32_t index;
    int64_t nonce;
    string data;
    string hash;
    time_t time;

    string calculateHash() const;
};
