import os
os.chdir('/home/ian/Documents/sandbox/cryptolabs/ans')
os.getcwd()
import set1.matasano_utils as mu

##      Start Set 1 challange 1
#
#
#       http://cryptopals.com/sets/1/challenges/1/
#       Convert hex to base64
#
##
test_hex_str="49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
mu.hex_to_base64(test_hex_str)
##      End Set 1 challange 1
#########################################################################    
#########################################################################    
#########################################################################    

##      Start Set 1 challange 2
#
#
#       http://cryptopals.com/sets/1/challenges/2/
#       Fixed XOR
#
##

test_hex_str1="1c0111001f010100061a024b53535009181c"
test_hex_str2="686974207468652062756c6c277320657965"
mu.fixed_xor(test_hex_str1, test_hex_str2)

##      End Set 1 challange 2
#########################################################################    
#########################################################################    
#########################################################################    

##      Start Set 1 challange 3
#
#
#       http://cryptopals.com/sets/1/challenges/3/
#       Single-byte XOR cipher
#
##

hex_str="1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
aa = mu.single_byte_xor_cipher(hex_str)
aa

from collections import Counter
freq_dict=Counter(aa[3])#{c : int(get(c, 0)) + 1 for c in aa[3]}
freq_dict

##      End Set 1 challange 3
#########################################################################    
#########################################################################    
#########################################################################    
##      Start Set 1 challange 4
#
#
#       http://cryptopals.com/sets/1/challenges/4/
#       Detect single-character XOR
#
##

lines = mu.read_file_to_list("/home/ian/Desktop/matasano/set1/data/xor-cipher-strings.txt")
ans_dict={}
with open("/home/ian/Desktop/matasano/set1/data/xor-decoded-cipher-strings.txt", "a") as myfile:
    for idx,line in enumerate(lines):
        ans = mu.single_byte_xor_cipher(line)
        ans_dict[ans[2]]=ans
        myfile.write(str(idx) + "   " + str(ans[2])  + "      " + ans[3] + "      " + line + "\n")
    myfile.close()
#
ans_dict[max(ans_dict.keys())]
##      End Set 1 challange 4
#########################################################################    
#########################################################################    
#########################################################################    

#########################################################################    
##      Start Set 1 challange 5
#
#
#       http://cryptopals.com/sets/1/challenges/5/
#       Implement repeating-key XOR
#
##
str1 = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal"
#print(str1)
key="ICE"
#format(ord(str1[0]) ^ ord(key[0]),'02x')                   #hex formatted integer
#format(ord(str1[0]) ^ ord(key[0]),'b').zfill(8)            #binary formatted integer
mu.repeated_key_xor(str1,key)
##      End Set 1 challange 5
#########################################################################    
#########################################################################    
#########################################################################    
#########################################################################    
##      Start Set 1 challange 6
#
#
#       http://cryptopals.com/sets/1/challenges/6/
#       Break repeating-key XOR
#

# 1 reverse unbase64 to binary string 
# 2 convert to ciphertext(ascii)
# 3 guess key length = N
# 4 break ciphertext (ascii) into blocks of length N
# 5 transpose the blocks to make ekeysize number of rows all xor with the same character
# 6 solve each row as if it waas a single-char XOR 
# 7 for each block the single-byte xor jey that produces the best char frequency histogram is prob the key for that block 
##

reload(set1.matasano_utils)

s1 = "this is a test"
s2 = "wokka wokka!!!"

set1.matasano_utils.base64_to_binary('=')
set1.matasano_utils.hamming_distance(s1, s2)

lines = mu.read_file_to_list("/home/ian/Desktop/matasano/set1/data/6.txt")
full_text=''.join(lines)
#aa = set1.matasano_utils.base64_str_to_binary_str(lines[0])
# ***************    1
full_text_binary = mu.base64_str_to_binary_str(full_text)

#chr(int('00000000',2) ^ ord('E'))
#chr(int('01001110',2) ^ ord('T'))
#chr(int('00011101',2) ^ ord('A'))

#chr(ord('t') ^ ord('N'))

#from collections import Counter
#binary_tokens = [full_text_binary[i:i+8] for i in range(0, len(full_text_binary), 8)]
#[ idx for idx,val in enumerate(binary_tokens) if val == '00000000']
#Counter(binary_tokens)

#len(full_text_binary)/8 #2876 ascii characters

import base64

# ***************    2
len(full_text)
cipher_text = base64.b64decode(full_text)
len(cipher_text)
cipher_text1 = mu.binary_str_to_ascii_str(full_text_binary)
len(cipher_text1)

freq_dict=Counter(cipher_text)
freq_dict.values()
tot=sum(freq_dict.values())
freq_dict_norm = { k: float(v)/tot for k,v in freq_dict.items()}
max(freq_dict_norm.values())

# *************** 3 guess keysize
N = 2
# *************** 4 break ciphertext (ascii) into blocks of length keysize
# *************** 5 transpose the blocks to make ekeysize number of rows all xor with the same character
map_char={i:[] for i in xrange(1,N+1)}
map_char
for i in xrange(0,len(cipher_text),N):
    for j in xrange(1,N+1):
            map_char[j].append(cipher_text[i+j-1])
map_str={i : ''.join(map_char[i]) for i in xrange(1,N+1)}

reload(set1.matasano_utils)
plain_text_map={}
for i in xrange(1,N+1):
    plain_text_map[i] = mu.single_byte_xor_cipher(mu.ascii_str_to_hex_str(map_str[i]))

len(cipher_text)
len(plain_text_map[2][3])
len(''.join([chr1 + chr2 + chr3 for chr1, chr2, chr3 in zip(plain_text_map[1][3],plain_text_map[2][3],plain_text_map[3][3])]))

                                                                        
                                                                                                                                                
                                                                                                                                                                                                                        
                                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                                        
                                                                                                                                                                                                                                                                                                                                                                                                                                                
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
print(str(i) + "  " + aa[i:i+8] + "   " + str(int(aa[i:i+8],2)) + "    " + chr(int(aa[i:i+8],2)))

byte_len=8
for j in xrange(2, 41, 1):
    KEYSIZE_bytes = j*byte_len
    edit_distance=1000000
    a=None
    b=None
    for i in xrange(0,len(aa),KEYSIZE_bytes*2):
        first_seq = aa[i : i+KEYSIZE_bytes]
        second_seq=aa[i+KEYSIZE_bytes : i+KEYSIZE_bytes*2]
        #print(first_seq + "\t" + second_seq)
        tmp = float(hamming_distance_bin_str(first_seq , second_seq))/j
        if tmp < edit_distance and tmp > 0:
            edit_distance = tmp
            a = first_seq 
            b = second_seq
        #print(float(hamming_distance(first_seq , second_seq))/KEYSIZE_bytes)
    print(str(j) + "   " + str(edit_distance))
                        
for KEYSIZE in range(2,41):
    print(KEYSIZE)

##      End Set 1 challange 6
#########################################################################    
#########################################################################    
#########################################################################    
#########################################################################    
##      Start Set 1 challange 7
#
#
#       http://cryptopals.com/sets/1/challenges/7/
#       AES in ECB mode
#       
#       https://en.wikipedia.org/wiki/Advanced_Encryption_Standard
#
##
reload(set1.matasano_utils)
key="YELLOW SUBMARINE"
len(set1.matasano_utils.ascii_str_to_binary_str(key))

lines = set1.matasano_utils.read_file_to_list("/home/ian/Desktop/matasano/set1/data/7.txt")
full_text=''.join(lines)



##      End Set 1 challange 7

for i in xrange(255):
    decoded_chr = chr(int(
        format(hex_to_decimal('3'),'b') +
        format(hex_to_decimal('7'),'b') + 
        format(hex_to_decimal('3'),'b'),2) ^ i)
    if decoded_chr == 'T':
        print(str(i) + "  key = " + chr(i))


chr(94)

[format(ord(chr(94)),'b').zfill(8) for char in hex_str]

int(format(ord(chr(94)),'b').zfill(8)[0:4],2)



hex_to_decimal('f')

["{0:b}".format(hex_to_decimal(ord(char)) for char in hex_str]

"{0:b}".format(hex_to_decimal(ord('1')))

aa = [format(hex_to_decimal(x), 'b') for x in hex_str]
[hex_to_decimal(x) for x in hex_str]

int(aa[0]+aa[1],2)

aa[67]

bb=[]
for i in xrange(0,len(aa),2):
  bb.append(int(aa[i]+aa[i+1],2))
  
for y in xrange(255):
    print('*'*50)
    ll=[]
    for x in bb:
        ll.append(chr(y^x))
    print(str(y) + ':::  ' + chr(y) + ':::  ' + ''.join(ll))

[(x,y) for x in bb for y in xrange(255)]

[(y,chr(y)) for y in xrange(255)]

llm={}
for y in hex_chrs:
    ll=[]
    yyy = hex_to_decimal(y)
    for x in hex_str:    
        xxx = hex_to_decimal(x)
        ll.append(chr(yyy^xxx))
    llm[y] = ll
    
    
    
len(llm['a'])