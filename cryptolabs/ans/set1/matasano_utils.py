import nltk
from nltk.corpus import brown
import numpy as np
import matplotlib.pyplot as plt
import re
from collections import Counter

#nltk.download()
#
#corpus_str=''.join(brown.words()).lower()
#corpus=re.sub('[^a-zA-Z]','',corpus_str)
#freq_dict=Counter(corpus)#{c : int(get(c, 0)) + 1 for c in aa[3]}
#freq_dict
#tot=sum(freq_dict.values())
#freq_dict_norm = { k: float(v)/tot for k,v in freq_dict.items()}

#freq_dict_norm = {
# u'a': 0.08065925146778459,
# u'b': 0.015340308576109877,
# u'c': 0.031018169681461667,
# u'd': 0.03967506460274998,
# u'e': 0.12497998284002623,
# u'f': 0.02331935924860217,
# u'g': 0.019508091989596132,
# u'h': 0.054200990828348006,
# u'i': 0.07285298049190801,
# u'j': 0.0016342430816480992,
# u'k': 0.006569631903391707,
# u'l': 0.04132932484437185,
# u'm': 0.025419896804165593,
# u'n': 0.07094629332766952,
# u'o': 0.07591497384705373,
# u'p': 0.02021353884846125,
# u'q': 0.0010752375510121519,
# u'r': 0.06131572160386757,
# u's': 0.06547781592978233,
# u't': 0.09249192149564849,
# u'u': 0.02713884407854481,
# u'v': 0.009958221026530533,
# u'w': 0.018782417263810153,
# u'x': 0.0019880200458161188,
# u'y': 0.017230349891528062,
# u'z': 0.0009593487301113713}

#
#total_num_chars = sum(freq_dict.values())
#
#X = np.arange(len(freq_dict))
#plt.bar(X, freq_dict.values(), align='center', width=0.5)
#plt.xticks(X, freq_dict.keys())
#ymax = max(freq_dict.values()) + 1
#plt.ylim(0, ymax)
#plt.show()
#
#import operator
#sorted_x = sorted(freq_dict.items(), key=operator.itemgetter(1))
#X = np.arange(len(sorted_x))
#plt.bar(X, sorted_x.values(), align='center', width=0.5)
#plt.xticks(X, sorted_x.keys())
#ymax = max(sorted_x.values()) + 1
#plt.ylim(0, ymax)
#plt.show()


letters_ordered_by_frequency="ETAOINSHRDLCUMWFGYPBVKJXQZ"
letters_ordered_by_frequency_map=dict(zip(letters_ordered_by_frequency,reversed(xrange(1, len(letters_ordered_by_frequency)+1))))

hex_chrs="0123456789abcdef"
hex_map=dict(zip(hex_chrs,xrange(0,len(hex_chrs))))
hex_map_inv=dict(zip(xrange(0,len(hex_chrs)), hex_chrs))

base64_str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
base64_map=dict(zip(base64_str,xrange(0,len(base64_str))))
base64_map_inv=dict(zip(xrange(0,len(base64_str)),base64_str))

def hex_to_base64(hex_str):
    bin_str = ''.join([format(hex_map[c],'b').zfill(4) for c in hex_str])
    base64_list = []
    for i in xrange(0,len(bin_str),6):
        base64_list.append(base64_map_inv[int(bin_str[i:i+6],2)])
    return ''.join(base64_list)

def fixed_xor(s1, s2):
    return ''.join([hex_map_inv[hex_map[ch1]^hex_map[ch2]] for ch1, ch2 in zip(s1, s2) ])

import re

def line_fitness(line):
    #count number of alphabetic chrs in the string
    return sum([1 for c in line if c.isalpha()])/float(len(line))
    #return sum([1 if ((c >= 'a' and c <= 'z') or (c >= 'A' and c <= 'Z') or c == ' ') else 0 for c in line])/float(len(line))
    #return sum([1 if (c >= ' ' and c <= '~') else 0 for c in line])/float(len(line))

def line_fitness2(str):
    #count of the chars with most frequent weighted
    return sum([letters_ordered_by_frequency_map[c]/26.0 for c in str.upper() if c.isalpha()])/len(str)
    
def single_byte_xor(ascii_str, key_chr):
    return [ascii_char ^ key_chr for ascii_char in ascii_str]

def single_byte_xor_all_ascii_char(ascii_str):
    return [(chr(i),single_byte_xor(ascii_str, chr(i))) for i in xrange(255)]        

def single_byte_xor_cipher_ascii(in_ascii_str):
    best_score = None
    best_key = None
    best_value = None
    for i in xrange(255):
        line=[]
        for j in xrange(0,len(in_ascii_str),1):       
            decoded_chr_ord = ord(in_ascii_str[j]) ^ i
            line.append(chr(decoded_chr_ord))
        #line = re.sub( '\s+', ' ', ''.join(line).strip())
        line = ''.join(line).strip()
        best_tmp = (line_fitness2(line)  + line_fitness(line)) /2
        if best_score == None or best_score < best_tmp:
            best_score = best_tmp
            best_key = i
            best_value = line
    return (best_key, chr(best_key), best_score, best_value)
    

def single_byte_xor_cipher(in_str):
    best_score = None
    best_key = None
    best_value = None
    for i in xrange(255):
        line=[]
        for j in xrange(0,len(in_str),2):       
            decoded_chr_ord = int(format(hex_map[in_str[j]],'b').zfill(4)+format(hex_map[in_str[j+1]],'b').zfill(4),2) ^ i
            line.append(chr(decoded_chr_ord))
        #line = re.sub( '\s+', ' ', ''.join(line).strip())
        line = ''.join(line).strip()
        best_tmp = (line_fitness2(line)  + line_fitness(line)) /2
        if best_score == None or best_score < best_tmp:
            best_score = best_tmp
            best_key = i
            best_value = line
    return (best_key, chr(best_key), best_score, best_value)

def read_file_to_list(file_path):
    f = open(file_path)
    return [line.strip() for line in f.readlines()]

def repeated_key_xor_ascii(str1, key):
    l=[]
    for i in xrange(0,len(str1),len(key)):
        for j in xrange(0,len(key)):
            if (i+j) < len(str1):
                l.append(chr(ord(str1[i+j]) ^ ord(key[j])))
    return ''.join(l)


def repeated_key_xor(str1, key):
    l=[]
    for i in xrange(0,len(str1),len(key)):
        for j in xrange(0,len(key)):
            if (i+j) < len(str1):
                l.append(format(ord(str1[i+j]) ^ ord(key[j]),'02x'))
    return ''.join(l)

def hamming_distance(str1, str2):
    #for each chr is str1/str2 XOR and count the number of 1's in the bit string, sum for each chr pair
    #assert (len(str1) == len(str2)), "str1 and str2 must be the same length"
    if len(str1) != len(str2):
        return [[1000000]]
    return sum([sum(l) for l in [[1 if c == '1' else 0 for c in a] for a in [format(ord(ch1) ^ ord(ch2),'b').zfill(8) for ch1, ch2 in zip(str1, str2)]]])

def hamming_distance_bin_str(bin_str1, bin_str2):
    #for each chr is str1/str2 XOR and count the number of 1's in the bit string, sum for each chr pair
    #assert (len(str1) == len(str2)), "str1 and str2 must be the same length"
    if len(bin_str1) != len(bin_str2):
        return 1000000
    return sum([ int(ch1) ^ int(ch2) for ch1, ch2 in zip(bin_str1, bin_str2)])

def ascii_to_binary(ascii_chr):
    return format(ord(ascii_chr),'b').zfill(8)

def ascii_str_to_binary_str(ascii_str):
    return ''.join([ascii_to_binary(c) for c in ascii_str])

def ascii_chr_to_hex_chr(ascii_chr):
    bin_str = ascii_to_binary(ascii_chr)
    return hex_map_inv[int(bin_str[0:4],2)]+hex_map_inv[int(bin_str[4:8],2)]

def ascii_str_to_hex_str(ascii_str):
    return ''.join([ascii_chr_to_hex_chr(ascii_chr) for ascii_chr in ascii_str])

def binary_to_ascii(binary_str):
    return chr(int(binary_str.zfill(8),2))

def binary_str_to_ascii_str(binary_str):
    return ''.join(binary_to_ascii(bin_str) for bin_str in [binary_str[i:i+8] for i in range(0, len(binary_str), 8)])

def base64_to_binary(base64_chr):
    #if base64_chr == "=":
    #    return ""#"00"
    #else:
    return format(base64_map[base64_chr],'b').zfill(6)

def base64_str_to_binary_str(base64_str):
    return ''.join([base64_to_binary(c) for c in base64_str if c != '='])
