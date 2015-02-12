import codecs
import shlex
import sys
import os
from subprocess import Popen, PIPE


def add_to_stash_users_group(ulist, encpwd):
    """
    :param ulist: List of Groups
    :param encpwd: Encoded password for Stash
    :return: None
    """

    password = codecs.decode(encpwd, 'rot_13')
    count = 0
    for user in ulist:
        count += 1

        # Add Stash User Permission to the list of users!
        cmd = './stash.sh -s https://gitscm.cisco.com -u automation -p ' + password + \
              ' -a addUserToGroup --userId ' + user + ' --group stash-users'
        args = shlex.split(cmd)
        p = Popen(args,
                  stdout=PIPE, stderr=PIPE, cwd=folder_path())
        output, err = p.communicate()
        rc = p.returncode
        if rc > 0:
            print(err.decode('utf-8'), end='')
        else:
            print(output.decode('utf-8'), end='')
    final_wrap_up(count)


def folder_path():
    """
    :return: Folder Path to be used as current working directory
    """
    path = os.getcwd()
    return path


def final_wrap_up(c):
    print()
    print('+' * 100)
    print("Number of total groups whose permissions were revoked:", c)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print('Usage: {} password'.format(sys.argv[0]))
        sys.exit(1)
    else:
        # You did not think I was going to give you the password that easy ;-)
        encoded_password = sys.argv[1]

        # Read the list of groups from the external text file
        f = open('userlist.txt', 'r')
        userlist = []
        for line in f:
            userlist.append(line.rstrip())
        f.close()

        # Let's add users to the stash-users group
        add_to_stash_users_group(userlist, encoded_password)