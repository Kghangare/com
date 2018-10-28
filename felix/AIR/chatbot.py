
# coding: utf-8

# In[48]:



import sqlite3


# In[49]:


connection=sqlite3.connect('chatbot.sqlite')
cursor=connection.cursor()


# In[42]:


cursor.execute('create table words (key text,value text)')


# In[50]:


print("hello")


# In[45]:


def check(tablename,text):
    cursor.execute('select value from words where key=?',(text,))
    row=cursor.fetchone()
    if row:
    
        return row[0]
    return False


# In[47]:


while(True):
    h=input()
    
    if h=='bye':
        break
    
    ans=check('words',h)
    
    if ans==False:
        print("I dont have answer for this please give anser")
        t=input()
        
        cursor.execute('insert into words values (?,?)',(h,t))
        connection.commit()
        
    else:
        print(ans)
        
    
    

