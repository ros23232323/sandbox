    *repeated key xor


lines = mu.read_file_to_list("/home/ian/Documents/sandbox/cryptolabs/set1/6.txt")
full_text=''.join(lines)
#aa = set1.matasano_utils.base64_str_to_binary_str(lines[0])
# ***************    1
full_text_binary = mu.base64_str_to_binary_str(full_text)

N = 2
byte_len=8
KEYSIZE_bytes = N*byte_len


full_text_binary[0:KEYSIZE_bytes]^full_text_binary[0:KEYSIZE_bytes]


















letters_ordered_by_frequency="ETAOINSHRDLCUMWFGYPBVKJXQZ"
letters_ordered_by_frequency_map=dict(zip(letters_ordered_by_frequency,reversed(xrange(1, len(letters_ordered_by_frequency)+1))))

hex_chrs="0123456789abcdef"
hex_map=dict(zip(hex_chrs,xrange(0,len(hex_chrs))))
hex_map_inv=dict(zip(xrange(0,len(hex_chrs)), hex_chrs))

base64_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
base64_map=dict(zip(base64_str,xrange(0,len(base64_str))))
base64_map_inv=dict(zip(xrange(0,len(base64_str)),base64_str))

hex_str="49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"

bin_str = ''.join([format(hex_map[c],'b').zfill(4) for c in hex_str])
base64_list = []
for i in xrange(0,len(bin_str),6):
    base64_list.append(base64_map_inv[int(bin_str[i:i+6],2)])
''.join(base64_list)


ord('a')
ord('A')
ord('a'.upper())

str="Cooking MC's like a pound of bacon"

[c  for c in str.upper() if c.isalpha()]


def line_fitness2(str):
    return sum([letters_ordered_by_frequency_map[c]/26.0 for c in str.upper() if c.isalpha()])/len(str)
    
def chr_freq_weight(c):
    return letters_ordered_by_frequency_map[c]
    


letters_ordered_by_frequency_map['E']



[i for i in reversed(xrange(1,10))]